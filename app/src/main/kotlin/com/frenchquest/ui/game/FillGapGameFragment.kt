package com.frenchquest.ui.game

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.frenchquest.data.models.Word
import com.frenchquest.databinding.FragmentFillGapBinding
import com.frenchquest.game.engines.AdaptiveDifficultyEngine
import com.frenchquest.game.engines.SpacedRepetitionEngine
import com.frenchquest.utils.GameResultEvent
import com.frenchquest.utils.TTSManager
import java.util.Locale

class FillGapGameFragment : Fragment() {

    private var _binding: FragmentFillGapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private data class FillGapQuestion(
        val word: Word, val sentenceWithGap: String,
        val correctAnswer: String, val choices: List<String>
    )

    private val questions = mutableListOf<FillGapQuestion>()
    private var currentIndex = 0
    private var correctCount = 0
    private var xpEarned = 0
    private var questionTimer: CountDownTimer? = null

    companion object {
        private const val TOTAL_QUESTIONS = 10
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFillGapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())
        binding.pbProgress.max = TOTAL_QUESTIONS

        val difficulty = viewModel.difficultyLevel
        val cefrLevel = AdaptiveDifficultyEngine.getCEFRLevel(difficulty)

        viewModel.loadRandomWords(cefrLevel, TOTAL_QUESTIONS + 10) { words ->
            if (!isAdded) return@loadRandomWords
            buildQuestions(words, difficulty)
            showQuestion(0)
        }
    }

    private fun buildQuestions(wordPool: List<Word>, difficulty: Int) {
        questions.clear()
        for (i in 0 until minOf(TOTAL_QUESTIONS, wordPool.size)) {
            val word = wordPool[i]
            if (word.exampleSentence.isNullOrEmpty()) continue

            val sentence = word.exampleSentence!!
            val target = word.french

            // Only create a gap if the target word actually appears in the sentence
            if (!sentence.lowercase(Locale.FRENCH).contains(target.lowercase(Locale.FRENCH))) continue

            val gap = sentence.replaceFirst(Regex("(?i)" + Regex.escape(target)), "___")
            val answer = target

            val distractors = wordPool.filter { it.id != word.id && !it.french.equals(answer, ignoreCase = true) }
                .shuffled()
                .take(3).map { it.french.split(" ")[0] }
                .toMutableList()
            while (distractors.size < 3) distractors.add("—")

            val choices = (listOf(answer) + distractors.take(3)).shuffled()
            questions.add(FillGapQuestion(word, gap, answer, choices))
        }
    }

    private fun showQuestion(index: Int) {
        if (index >= questions.size) { endGame(); return }
        currentIndex = index
        val q = questions[index]

        binding.pbProgress.progress = index
        binding.tvProgress.text = "${index + 1} / ${questions.size}"
        binding.tvFeedback.visibility = View.INVISIBLE
        binding.tvSentence.text = q.sentenceWithGap
        binding.tvTranslation.text = q.word.exampleTranslation ?: ""

        binding.tvQuestion.text = if (AdaptiveDifficultyEngine.showPhoneticHint(viewModel.difficultyLevel))
            "Fill the gap — \"${q.word.english}\"" else "Fill the gap:"

        binding.tvTimer.text = ""
        binding.tvTimer.setTextColor(Color.parseColor("#A0A0B0"))

        if (AdaptiveDifficultyEngine.autoPlayAudio(viewModel.difficultyLevel))
            tts.speak(q.sentenceWithGap.replace("___", "..."))

        buildChoiceButtons(q)

        val timeLimit = AdaptiveDifficultyEngine.getTimeLimitMs(viewModel.difficultyLevel)
        if (timeLimit > 0) startQuestionTimer(timeLimit)
    }

    private fun buildChoiceButtons(q: FillGapQuestion) {
        binding.layoutChoices.removeAllViews()
        for (choice in q.choices) {
            val btn = Button(requireContext()).apply {
                text = choice
                setTextColor(Color.parseColor("#EAEAEA"))
                setBackgroundColor(Color.parseColor("#1E3A5F"))
                textSize = 16f
                setPadding(16, 24, 16, 24)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 8, 0, 8) }
                setOnClickListener { onChoiceSelected(this, choice, q) }
            }
            binding.layoutChoices.addView(btn)
        }
    }

    private fun onChoiceSelected(btn: Button, choice: String, q: FillGapQuestion) {
        for (i in 0 until binding.layoutChoices.childCount)
            binding.layoutChoices.getChildAt(i).isEnabled = false
        questionTimer?.cancel()

        val correct = choice.equals(q.correctAnswer, ignoreCase = true)
        btn.setBackgroundColor(Color.parseColor(if (correct) "#2ECC71" else "#E74C3C"))

        if (correct) {
            correctCount++
            val xp = (8 * AdaptiveDifficultyEngine.getXPMultiplier(viewModel.difficultyLevel)).toInt()
            xpEarned += xp
            SpacedRepetitionEngine.processAnswer(q.word, SpacedRepetitionEngine.QUALITY_CORRECT)
            viewModel.updateWord(q.word)
            binding.tvFeedback.apply {
                visibility = View.VISIBLE
                setTextColor(Color.parseColor("#2ECC71"))
                text = "✓ Correct! (+$xp XP)\n${q.word.french} = ${q.word.english}"
            }
            tts.speak(q.word.exampleSentence ?: q.word.french)
        } else {
            for (i in 0 until binding.layoutChoices.childCount) {
                val b = binding.layoutChoices.getChildAt(i) as Button
                if (b.text.toString().equals(q.correctAnswer, ignoreCase = true))
                    b.setBackgroundColor(Color.parseColor("#2ECC71"))
            }
            SpacedRepetitionEngine.processAnswer(q.word, SpacedRepetitionEngine.QUALITY_WRONG)
            viewModel.updateWord(q.word)
            binding.tvFeedback.apply {
                visibility = View.VISIBLE
                setTextColor(Color.parseColor("#E74C3C"))
                text = "✗ The answer was: ${q.correctAnswer}\n📖 ${q.word.english}"
            }
            tts.speak(q.word.exampleSentence ?: q.word.french)
        }
        btn.postDelayed({ showQuestion(currentIndex + 1) }, 1800)
    }

    private fun startQuestionTimer(timeLimitMs: Int) {
        questionTimer?.cancel()
        questionTimer = object : CountDownTimer(timeLimitMs.toLong(), 1000) {
            override fun onTick(ms: Long) {
                val secs = (ms / 1000).toInt()
                binding.tvTimer.text = "⏱ $secs"
                if (secs <= 3) binding.tvTimer.setTextColor(Color.parseColor("#E74C3C"))
            }
            override fun onFinish() {
                val q = questions[currentIndex]
                SpacedRepetitionEngine.processAnswer(q.word, SpacedRepetitionEngine.QUALITY_WRONG)
                viewModel.updateWord(q.word)
                binding.tvFeedback.apply {
                    visibility = View.VISIBLE
                    text = "⏰ Time's up! Answer: ${q.correctAnswer}"
                }
                binding.tvFeedback.postDelayed({ showQuestion(currentIndex + 1) }, 1500)
            }
        }.start()
    }

    private fun endGame() {
        questionTimer?.cancel()
        viewModel.onGameCompleted(
            GameResultEvent("fill_gap", correctCount, questions.size, xpEarned)
        )
        binding.tvFeedback.apply {
            visibility = View.VISIBLE
            setTextColor(Color.parseColor("#FFE66D"))
            text = "🎉 Game over! $correctCount/${questions.size} correct • +$xpEarned XP"
        }
        binding.tvFeedback.postDelayed({
            if (isAdded) findNavController().navigateUp()
        }, 2500)
    }

    override fun onDestroyView() { super.onDestroyView(); questionTimer?.cancel(); _binding = null }
}
