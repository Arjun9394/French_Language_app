package com.frenchquest.ui.game

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.frenchquest.data.models.Word
import com.frenchquest.databinding.FragmentListenTapBinding
import com.frenchquest.game.engines.AdaptiveDifficultyEngine
import com.frenchquest.game.engines.SpacedRepetitionEngine
import com.frenchquest.utils.GameResultEvent
import com.frenchquest.utils.TTSManager

class ListenTapGameFragment : Fragment() {

    private var _binding: FragmentListenTapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private var wordList = mutableListOf<Word>()
    private var currentIndex = 0
    private var correctCount = 0
    private var xpEarned = 0
    private var currentWord: Word? = null
    private var currentChoiceWords = mutableListOf<Word>()

    companion object {
        private const val TOTAL = 10
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListenTapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())
        binding.pbProgress.max = TOTAL

        val diff = viewModel.difficultyLevel
        val level = AdaptiveDifficultyEngine.getCEFRLevel(diff)

        viewModel.loadRandomWords(level, TOTAL + 10) { words ->
            if (!isAdded) return@loadRandomWords
            wordList = words.toMutableList()
            showQuestion(0)
        }

        binding.btnReplay.setOnClickListener {
            currentWord?.let { tts.speakSlow(it.french) }
        }
    }

    private fun showQuestion(index: Int) {
        if (index >= TOTAL || index >= wordList.size) { endGame(); return }
        currentIndex = index
        currentWord = wordList[index]

        binding.pbProgress.progress = index
        binding.tvProgress.text = "${index + 1} / $TOTAL"
        binding.tvSubtitle.text = ""
        binding.tvFeedback.visibility = View.INVISIBLE

        val diff = viewModel.difficultyLevel
        val showEnglish = diff >= 3

        binding.tvInstruction.text = if (showEnglish)
            "🎧 Écoute… puis tape la traduction anglaise"
        else "🎧 Écoute… puis tape le mot français"

        currentChoiceWords = mutableListOf(currentWord!!).also { choices ->
            val pool = wordList.toMutableList().apply { remove(currentWord) }.shuffled()
            choices.addAll(pool.take(3))
            choices.shuffle()
        }

        buildChoiceButtons(showEnglish)

        binding.root.postDelayed({
            if (!isAdded) return@postDelayed
            currentWord?.let { word ->
                tts.speak(word.french)
                // Only reveal the French text at easier difficulty levels
                if (viewModel.difficultyLevel <= 2) {
                    binding.tvSubtitle.postDelayed({
                        if (isAdded) binding.tvSubtitle.text = "🗣 ${word.french}"
                    }, 1500)
                }
            }
        }, 400)
    }

    private fun buildChoiceButtons(showEnglish: Boolean) {
        binding.layoutChoices.removeAllViews()
        for (w in currentChoiceWords) {
            val btn = Button(requireContext()).apply {
                text = if (showEnglish) w.english else w.french
                setTextColor(Color.parseColor("#EAEAEA"))
                setBackgroundColor(Color.parseColor("#1E3A5F"))
                textSize = 16f
                setPadding(16, 24, 16, 24)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 10, 0, 10) }
                setOnClickListener { onChoiceTapped(this, w) }
            }
            binding.layoutChoices.addView(btn)
        }
    }

    private fun onChoiceTapped(btn: Button, chosen: Word) {
        for (i in 0 until binding.layoutChoices.childCount)
            binding.layoutChoices.getChildAt(i).isEnabled = false

        val correct = chosen.id == currentWord?.id

        btn.setBackgroundColor(Color.parseColor(if (correct) "#2ECC71" else "#E74C3C"))

        if (correct) {
            correctCount++; xpEarned += 10
            SpacedRepetitionEngine.processAnswer(currentWord!!, SpacedRepetitionEngine.QUALITY_CORRECT)
            binding.tvFeedback.apply {
                visibility = View.VISIBLE
                setTextColor(Color.parseColor("#2ECC71"))
                text = "✓ ${currentWord!!.french} = ${currentWord!!.english}"
            }
        } else {
            SpacedRepetitionEngine.processAnswer(currentWord!!, SpacedRepetitionEngine.QUALITY_WRONG)
            for (i in 0 until binding.layoutChoices.childCount) {
                val b = binding.layoutChoices.getChildAt(i) as Button
                if (currentChoiceWords[i].id == currentWord?.id)
                    b.setBackgroundColor(Color.parseColor("#2ECC71"))
            }
            binding.tvFeedback.apply {
                visibility = View.VISIBLE
                setTextColor(Color.parseColor("#E74C3C"))
                text = "✗ C'était : ${currentWord!!.french} = ${currentWord!!.english}"
            }
        }

        currentWord?.let { viewModel.updateWord(it) }
        tts.speak(currentWord!!.french)
        btn.postDelayed({ showQuestion(currentIndex + 1) }, 1800)
    }

    private fun endGame() {
        viewModel.onGameCompleted(GameResultEvent("listen_tap", correctCount, TOTAL, xpEarned))
        binding.tvFeedback.apply {
            visibility = View.VISIBLE
            setTextColor(Color.parseColor("#FFE66D"))
            text = "🎉 $correctCount/$TOTAL correct • +$xpEarned XP"
        }
        binding.tvFeedback.postDelayed({
            if (isAdded) findNavController().navigateUp()
        }, 2500)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
