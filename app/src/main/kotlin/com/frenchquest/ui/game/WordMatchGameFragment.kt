package com.frenchquest.ui.game

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.frenchquest.data.models.Word
import com.frenchquest.databinding.FragmentWordMatchBinding
import com.frenchquest.game.engines.AdaptiveDifficultyEngine
import com.frenchquest.game.engines.SpacedRepetitionEngine
import com.frenchquest.utils.GameResultEvent
import com.frenchquest.utils.TTSManager

class WordMatchGameFragment : Fragment() {

    private var _binding: FragmentWordMatchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private var currentWords = mutableListOf<Word>()
    private val frenchButtons = mutableListOf<Button>()
    private val englishButtons = mutableListOf<Button>()

    private var selectedFrenchBtn: Button? = null
    private var selectedFrenchWord: Word? = null
    private var matchedCount = 0
    private var correctCount = 0
    private var incorrectCount = 0
    private val totalPairs = 8
    private var xpEarned = 0
    private var timer: CountDownTimer? = null

    companion object {
        private const val GAME_TIME_MS = 120_000L
        private const val COLOR_DEFAULT = "#1E3A5F"
        private const val COLOR_SELECTED = "#FF6B35"
        private const val COLOR_CORRECT = "#2ECC71"
        private const val COLOR_WRONG = "#E74C3C"
        private const val COLOR_TEXT = "#EAEAEA"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWordMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())

        val difficulty = viewModel.difficultyLevel
        val cefrLevel = AdaptiveDifficultyEngine.getCEFRLevel(difficulty)

        viewModel.loadRandomWords(cefrLevel, totalPairs + 4) { words ->
            if (!isAdded) return@loadRandomWords
            currentWords = words.take(totalPairs).toMutableList()
            setupGame()
        }
    }

    private fun setupGame() {
        frenchButtons.clear()
        englishButtons.clear()
        binding.gridFrench.removeAllViews()
        binding.gridEnglish.removeAllViews()

        val shuffledFrench = currentWords.shuffled()
        val shuffledEnglish = currentWords.shuffled()

        for (word in shuffledFrench) {
            val btn = createCardButton(word.french)
            btn.setOnClickListener { onFrenchSelected(btn, word) }
            binding.gridFrench.addView(btn)
            frenchButtons.add(btn)
        }

        for (word in shuffledEnglish) {
            val btn = createCardButton(word.english)
            btn.setOnClickListener { onEnglishSelected(btn, word) }
            binding.gridEnglish.addView(btn)
            englishButtons.add(btn)
        }

        binding.pbProgress.max = totalPairs
        binding.pbProgress.progress = 0
        binding.tvScore.text = "0 / $totalPairs"

        startTimer()
    }

    private fun createCardButton(text: String): Button {
        return Button(requireContext()).apply {
            this.text = text
            setTextColor(Color.parseColor(COLOR_TEXT))
            setBackgroundColor(Color.parseColor(COLOR_DEFAULT))
            textSize = 14f
            setPadding(16, 20, 16, 20)
            layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f)
                setMargins(4, 4, 4, 4)
            }
        }
    }

    private fun onFrenchSelected(btn: Button, word: Word) {
        selectedFrenchBtn?.setBackgroundColor(Color.parseColor(COLOR_DEFAULT))
        selectedFrenchBtn = btn
        selectedFrenchWord = word
        btn.setBackgroundColor(Color.parseColor(COLOR_SELECTED))
        tts.speak(word.french)
    }

    private fun onEnglishSelected(btn: Button, word: Word) {
        val frenchWord = selectedFrenchWord ?: return
        val frenchBtn = selectedFrenchBtn ?: return

        if (frenchWord.id == word.id) {
            // Match!
            correctCount++
            matchedCount++
            xpEarned += (8 * AdaptiveDifficultyEngine.getXPMultiplier(viewModel.difficultyLevel)).toInt()

            frenchBtn.setBackgroundColor(Color.parseColor(COLOR_CORRECT))
            btn.setBackgroundColor(Color.parseColor(COLOR_CORRECT))
            frenchBtn.isEnabled = false
            btn.isEnabled = false

            SpacedRepetitionEngine.processAnswer(frenchWord, SpacedRepetitionEngine.QUALITY_CORRECT)
            viewModel.updateWord(frenchWord)

            binding.tvFeedback.setTextColor(Color.parseColor(COLOR_CORRECT))
            binding.tvFeedback.text = "✓ ${frenchWord.french} = ${frenchWord.english}"
            binding.tvFeedback.visibility = View.VISIBLE
        } else {
            // Mismatch
            incorrectCount++
            frenchBtn.setBackgroundColor(Color.parseColor(COLOR_WRONG))
            btn.setBackgroundColor(Color.parseColor(COLOR_WRONG))

            SpacedRepetitionEngine.processAnswer(frenchWord, SpacedRepetitionEngine.QUALITY_WRONG)
            viewModel.updateWord(frenchWord)

            binding.tvFeedback.setTextColor(Color.parseColor(COLOR_WRONG))
            binding.tvFeedback.text = "✗ Try again!"
            binding.tvFeedback.visibility = View.VISIBLE

            btn.postDelayed({
                frenchBtn.setBackgroundColor(Color.parseColor(COLOR_DEFAULT))
                btn.setBackgroundColor(Color.parseColor(COLOR_DEFAULT))
            }, 600)

            tts.speakSlow(frenchWord.french)
        }

        selectedFrenchBtn = null
        selectedFrenchWord = null

        binding.pbProgress.progress = matchedCount
        binding.tvScore.text = "$matchedCount / $totalPairs"

        if (matchedCount >= currentWords.size) endGame()
    }

    private fun startTimer() {
        binding.tvTimer.setTextColor(Color.parseColor("#A0A0B0"))
        timer = object : CountDownTimer(GAME_TIME_MS, 1000) {
            override fun onTick(ms: Long) {
                val secs = (ms / 1000).toInt()
                binding.tvTimer.text = "⏱ ${secs / 60}:${"%02d".format(secs % 60)}"
                if (secs <= 10) binding.tvTimer.setTextColor(Color.parseColor(COLOR_WRONG))
            }
            override fun onFinish() { endGame() }
        }.start()
    }

    private fun endGame() {
        timer?.cancel()
        viewModel.onGameCompleted(
            GameResultEvent("word_match", correctCount, correctCount + incorrectCount, xpEarned)
        )
        binding.tvFeedback.setTextColor(Color.parseColor("#FFE66D"))
        binding.tvFeedback.text = "🎉 Game over! $correctCount matched • +$xpEarned XP"
        binding.tvFeedback.visibility = View.VISIBLE

        // Disable all remaining buttons
        for (i in 0 until binding.gridFrench.childCount) binding.gridFrench.getChildAt(i).isEnabled = false
        for (i in 0 until binding.gridEnglish.childCount) binding.gridEnglish.getChildAt(i).isEnabled = false

        binding.tvFeedback.postDelayed({
            if (isAdded) findNavController().navigateUp()
        }, 2500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        _binding = null
    }
}
