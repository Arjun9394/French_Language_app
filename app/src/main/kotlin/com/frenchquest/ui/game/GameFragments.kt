package com.frenchquest.ui.game

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.frenchquest.data.models.Word
import com.frenchquest.databinding.*
import com.frenchquest.game.content.DialogueContent
import com.frenchquest.game.engines.AdaptiveDifficultyEngine
import com.frenchquest.game.engines.SpacedRepetitionEngine
import com.frenchquest.utils.GameResultEvent
import com.frenchquest.utils.TTSManager
import java.util.Locale

// ═══════════════════════════════════════════════
// DIALOGUE GAME
// ═══════════════════════════════════════════════
class DialogueGameFragment : Fragment() {

    private var _binding: FragmentDialogueBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private var allDialogues = listOf<DialogueContent.Dialogue>()
    private var dialogueIndex = 0
    private var exchangeIndex = 0
    private var correctCount = 0
    private var totalCount = 0
    private var xpEarned = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDialogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())
        allDialogues = DialogueContent.getAllDialogues().shuffled()
        loadDialogue(0)
    }

    private fun loadDialogue(index: Int) {
        if (index >= allDialogues.size || index >= 3) { endGame(); return }
        dialogueIndex = index; exchangeIndex = 0
        val d = allDialogues[index]
        binding.tvScenarioTitle.text = "${d.emoji}  ${d.title}"
        binding.tvScenarioContext.text = d.context
        showExchange(0)
    }

    private fun showExchange(idx: Int) {
        val d = allDialogues[dialogueIndex]
        if (idx >= d.exchanges.size) {
            binding.tvFeedback.text = "✅ Dialogue terminé !"
            binding.tvFeedback.postDelayed({ loadDialogue(dialogueIndex + 1) }, 1400)
            return
        }
        exchangeIndex = idx
        val ex = d.exchanges[idx]
        binding.tvSpeakerLine.text = "💬 ${ex.npcLine}"
        binding.tvScenarioContext.text = ex.npcLineTranslation?.let { "($it)" } ?: ""
        binding.tvProgress.text = "Dialogue ${dialogueIndex + 1}/3  •  Line ${idx + 1}/${d.exchanges.size}"
        binding.tvFeedback.text = ""
        tts.speak(ex.npcLine)

        binding.layoutChoices.removeAllViews()
        ex.choices.forEachIndexed { i, choice ->
            val btn = Button(requireContext()).apply {
                text = "${choice.french}\n${choice.english}"
                setTextColor(Color.parseColor("#EAEAEA"))
                setBackgroundColor(Color.parseColor(if (i % 2 == 0) "#1E3A5F" else "#243B6E"))
                setPadding(24, 20, 24, 20); textSize = 14f
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 10, 0, 10) }
                setOnClickListener { onChoiceSelected(this, choice, ex) }
            }
            binding.layoutChoices.addView(btn)
        }
    }

    private fun onChoiceSelected(btn: Button, choice: DialogueContent.Choice, ex: DialogueContent.Exchange) {
        for (i in 0 until binding.layoutChoices.childCount) binding.layoutChoices.getChildAt(i).isEnabled = false
        totalCount++
        if (choice.isCorrect) {
            correctCount++; xpEarned += 10
            btn.setBackgroundColor(Color.parseColor("#2ECC71"))
            binding.tvFeedback.setTextColor(Color.parseColor("#2ECC71"))
            binding.tvFeedback.text = "✓ ${choice.consequence ?: "Parfait !"}  (+10 XP)"
            tts.speak(choice.french)
        } else {
            btn.setBackgroundColor(Color.parseColor("#E74C3C"))
            val correctFrench = ex.choices.firstOrNull { it.isCorrect }?.french ?: "?"
            ex.choices.forEachIndexed { i, c ->
                if (c.isCorrect) (binding.layoutChoices.getChildAt(i) as? Button)
                    ?.setBackgroundColor(Color.parseColor("#2ECC71"))
            }
            binding.tvFeedback.setTextColor(Color.parseColor("#E74C3C"))
            binding.tvFeedback.text = "✗ La bonne réponse : $correctFrench"
            tts.speakSlow(correctFrench)
        }
        btn.postDelayed({ showExchange(exchangeIndex + 1) }, 1800)
    }

    private fun endGame() {
        viewModel.onGameCompleted(GameResultEvent("dialogue", correctCount, totalCount, xpEarned))
        binding.tvSpeakerLine.text = "🎉 Tous les dialogues terminés !"
        binding.tvFeedback.text = "Score : $correctCount/$totalCount  •  +$xpEarned XP"
        binding.layoutChoices.removeAllViews()
        binding.tvFeedback.postDelayed({
            if (isAdded) findNavController().navigateUp()
        }, 2500)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

// ═══════════════════════════════════════════════
// PRONOUNCE GAME
// ═══════════════════════════════════════════════
class PronounceGameFragment : Fragment() {

    private var _binding: FragmentPronounceBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private var wordList = mutableListOf<Word>()
    private var currentIndex = 0
    private var correctCount = 0
    private var xpEarned = 0
    private var speechRecognizer: SpeechRecognizer? = null

    companion object {
        private const val TOTAL = 8
        private const val PERMISSION_REQUEST_CODE = 101
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPronounceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())
        binding.pbProgress.max = TOTAL
        binding.btnNext.visibility = View.GONE

        setupSpeechRecognizer()
        checkMicPermission()

        val diff = viewModel.difficultyLevel
        viewModel.loadRandomWords(AdaptiveDifficultyEngine.getCEFRLevel(diff), TOTAL + 5) { words ->
            if (!isAdded) return@loadRandomWords
            wordList = words.toMutableList(); showWord(0)
        }

        binding.btnListen.setOnClickListener { wordList.getOrNull(currentIndex)?.let { tts.speakSlow(it.french) } }
        binding.btnMic.setOnClickListener { startListening() }
        binding.btnNext.setOnClickListener { showWord(currentIndex + 1) }
    }

    private fun showWord(index: Int) {
        if (index >= TOTAL || index >= wordList.size) { endGame(); return }
        currentIndex = index
        val w = wordList[index]
        binding.tvProgress.text = "${index + 1}/$TOTAL"
        binding.pbProgress.progress = index
        binding.tvWordToSay.text = w.french
        binding.tvPhonetic.text = w.phonetic?.let { "[$it]" } ?: ""
        binding.tvEnglish.text = w.english
        binding.tvStatus.text = "Écoute d'abord, puis appuie sur 🎤"
        binding.tvResult.text = ""
        binding.btnNext.visibility = View.GONE
        tts.speak(w.french)
    }

    private fun setupSpeechRecognizer() {
        if (!SpeechRecognizer.isRecognitionAvailable(requireContext())) {
            binding.tvStatus.text = "⚠️ Speech recognition not available."
            return
        }
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext()).apply {
            setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(p: Bundle?) { binding.tvStatus.text = "🎤 Parlez maintenant…" }
                override fun onBeginningOfSpeech() { binding.tvStatus.text = "🎙️ En train d'écouter…" }
                override fun onEndOfSpeech() { binding.tvStatus.text = "⏳ Analyse…" }
                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) processRecognitionResult(matches[0])
                    else binding.tvStatus.text = "❓ Pas compris. Réessaie !"
                    binding.btnNext.visibility = View.VISIBLE
                }
                override fun onError(error: Int) {
                    binding.tvStatus.text = "⚠️ Erreur. Réessaie."
                    binding.btnNext.visibility = View.VISIBLE
                }
                override fun onRmsChanged(r: Float) {}
                override fun onBufferReceived(b: ByteArray?) {}
                override fun onPartialResults(p: Bundle?) {}
                override fun onEvent(t: Int, b: Bundle?) {}
            })
        }
    }

    private fun startListening() {
        if (!checkMicPermission()) return
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr-FR")
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
        }
        speechRecognizer?.startListening(intent)
    }

    private fun processRecognitionResult(recognized: String) {
        val target = wordList[currentIndex]
        val spoken = recognized.lowercase(Locale.FRENCH).trim()
        val expected = target.french.lowercase(Locale.FRENCH).trim()
        val dist = levenshtein(spoken, expected)
        val maxLen = maxOf(spoken.length, expected.length)
        val pct = if (maxLen == 0) 100 else (100 - (dist * 100 / maxLen)).coerceAtLeast(0)
        val correct = pct >= 70

        if (correct) {
            correctCount++; xpEarned += (10 * (pct / 100.0)).toInt()
            SpacedRepetitionEngine.processAnswer(target, SpacedRepetitionEngine.QUALITY_CORRECT)
            binding.tvResult.text = "✅ $pct% — Super !\nTu as dit : \"$recognized\""
            binding.tvResult.setTextColor(Color.parseColor("#2ECC71"))
        } else {
            SpacedRepetitionEngine.processAnswer(target, SpacedRepetitionEngine.QUALITY_WRONG)
            binding.tvResult.text = "❌ $pct%\nTu as dit : \"$recognized\"\nCorrect : \"${target.french}\""
            binding.tvResult.setTextColor(Color.parseColor("#E74C3C"))
            tts.speakSlow(target.french)
        }
        viewModel.updateWord(target)
    }

    private fun levenshtein(a: String, b: String): Int {
        val dp = IntArray(b.length + 1) { it }
        for (i in 1..a.length) {
            var prev = i
            for (j in 1..b.length) {
                val cur = minOf(
                    dp[j] + 1, prev + 1,
                    dp[j - 1] + if (a[i - 1] == b[j - 1]) 0 else 1
                )
                dp[j - 1] = prev; prev = cur
            }
            dp[b.length] = prev
        }
        return dp[b.length]
    }

    private fun checkMicPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO), PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    private fun endGame() {
        viewModel.onGameCompleted(GameResultEvent("pronounce", correctCount, TOTAL, xpEarned))
        binding.tvWordToSay.text = "🎉 Session terminée !"
        binding.tvResult.text = "$correctCount/$TOTAL correct • +$xpEarned XP"
        binding.btnMic.visibility = View.GONE
        binding.btnListen.visibility = View.GONE
        binding.tvResult.postDelayed({
            if (isAdded) findNavController().navigateUp()
        }, 2500)
    }

    override fun onDestroyView() { super.onDestroyView(); speechRecognizer?.destroy(); _binding = null }
}

// ═══════════════════════════════════════════════
// QUIZ BLITZ
// ═══════════════════════════════════════════════
class QuizBlitzFragment : Fragment() {

    private var _binding: FragmentQuizBlitzBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private var words = mutableListOf<Word>()
    private var currentIndex = 0
    private var correctCount = 0
    private var xpEarned = 0
    private var streakCount = 0
    private var timer: CountDownTimer? = null
    private val startTime = System.currentTimeMillis()

    companion object { private const val TOTAL = 10 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentQuizBlitzBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())
        binding.pbProgress.max = TOTAL

        viewModel.loadRandomWords(AdaptiveDifficultyEngine.getCEFRLevel(viewModel.difficultyLevel), TOTAL + 10) { w ->
            if (!isAdded) return@loadRandomWords
            words = w.toMutableList(); showQuestion(0)
        }
    }

    private fun showQuestion(idx: Int) {
        if (idx >= TOTAL || idx >= words.size) { endGame(); return }
        currentIndex = idx
        val word = words[idx]
        binding.pbProgress.progress = idx
        binding.tvProgress.text = "${idx + 1}/$TOTAL"
        binding.tvQuestion.text = "🇬🇧  ${word.english}"
        binding.tvStreak.text = if (streakCount > 1) "🔥 $streakCount streak!" else ""
        binding.tvFeedback.text = ""
        tts.speak(word.french)

        val choices = mutableListOf(word).also { list ->
            list.addAll(words.filter { it.id != word.id }.shuffled().take(3))
            list.shuffle()
        }

        binding.layoutChoices.removeAllViews()
        for (c in choices) {
            val btn = Button(requireContext()).apply {
                text = c.french
                setTextColor(Color.parseColor("#EAEAEA"))
                setBackgroundColor(Color.parseColor("#1E3A5F"))
                textSize = 15f
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 8, 0, 8) }
                setOnClickListener { onAnswer(this, c, word) }
            }
            binding.layoutChoices.addView(btn)
        }

        timer?.cancel()
        timer = object : CountDownTimer(8000, 100) {
            override fun onTick(ms: Long) {
                val secs = ms / 1000 + 1
                binding.tvTimer.text = "⏱ $secs"
                if (secs <= 3) binding.tvTimer.setTextColor(Color.parseColor("#E74C3C"))
                else binding.tvTimer.setTextColor(Color.parseColor("#FFE66D"))
            }
            override fun onFinish() {
                streakCount = 0
                SpacedRepetitionEngine.processAnswer(word, SpacedRepetitionEngine.QUALITY_WRONG)
                viewModel.updateWord(word)
                binding.tvFeedback.text = "⏰ Too slow!"
                binding.tvFeedback.postDelayed({ showQuestion(idx + 1) }, 800)
            }
        }.start()
    }

    private fun onAnswer(btn: Button, chosen: Word, target: Word) {
        timer?.cancel()
        for (i in 0 until binding.layoutChoices.childCount) binding.layoutChoices.getChildAt(i).isEnabled = false

        if (chosen.id == target.id) {
            correctCount++; streakCount++
            val xp = (10 * (1 + streakCount * 0.2)).toInt()
            xpEarned += xp
            btn.setBackgroundColor(Color.parseColor("#2ECC71"))
            SpacedRepetitionEngine.processAnswer(target, SpacedRepetitionEngine.QUALITY_CORRECT)
            binding.tvFeedback.text = "✓ +$xp XP"
        } else {
            streakCount = 0
            btn.setBackgroundColor(Color.parseColor("#E74C3C"))
            SpacedRepetitionEngine.processAnswer(target, SpacedRepetitionEngine.QUALITY_WRONG)
            binding.tvFeedback.text = "✗ ${target.french}"
        }
        viewModel.updateWord(target)
        btn.postDelayed({ showQuestion(currentIndex + 1) }, 800)
    }

    private fun endGame() {
        timer?.cancel()
        val duration = System.currentTimeMillis() - startTime
        viewModel.onGameCompleted(GameResultEvent("quiz_blitz", correctCount, TOTAL, xpEarned, duration))
        binding.tvQuestion.text = "🎉 Quiz Blitz terminé !"
        binding.tvFeedback.text = "$correctCount/$TOTAL correct • +$xpEarned XP"
        binding.layoutChoices.removeAllViews()
        binding.tvFeedback.postDelayed({
            if (isAdded) findNavController().navigateUp()
        }, 2500)
    }

    override fun onDestroyView() { super.onDestroyView(); timer?.cancel(); _binding = null }
}

// ═══════════════════════════════════════════════
// FLASHCARD GAME
// ═══════════════════════════════════════════════
class FlashcardGameFragment : Fragment() {

    private var _binding: FragmentFlashcardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private var words = mutableListOf<Word>()
    private var currentIndex = 0
    private var isFlipped = false
    private var sessionCorrect = 0
    private var xpEarned = 0

    companion object { private const val TOTAL = 10 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFlashcardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())

        binding.cardFront.setOnClickListener { flipCard() }
        binding.cardBack.setOnClickListener { flipCard() }
        binding.btnAudio.setOnClickListener { words.getOrNull(currentIndex)?.let { tts.speakSlow(it.french) } }
        binding.btnEasy.setOnClickListener { rateCard(SpacedRepetitionEngine.QUALITY_CORRECT) }
        binding.btnOk.setOnClickListener { rateCard(SpacedRepetitionEngine.QUALITY_CORRECT_SLOW) }
        binding.btnHard.setOnClickListener { rateCard(SpacedRepetitionEngine.QUALITY_WRONG) }

        viewModel.loadDueWords(TOTAL) { due ->
            if (!isAdded) return@loadDueWords
            words = due.toMutableList()
            if (words.size < TOTAL) {
                viewModel.loadRandomWords("A1", TOTAL - words.size) { extra ->
                    if (!isAdded) return@loadRandomWords
                    val ids = words.map { it.id }.toSet()
                    words.addAll(extra.filter { it.id !in ids })
                    showCard(0)
                }
            } else showCard(0)
        }
    }

    private fun showCard(idx: Int) {
        if (idx >= words.size || idx >= TOTAL) { endSession(); return }
        currentIndex = idx; isFlipped = false
        val word = words[idx]
        binding.tvProgress.text = "${idx + 1} / ${minOf(TOTAL, words.size)}"
        binding.tvHint.text = "Tap card to reveal answer"
        binding.tvFrench.text = word.french
        binding.tvPhonetic.text = word.phonetic ?: ""
        binding.tvEnglish.text = word.english
        binding.tvExample.text = word.exampleSentence?.let { "\"$it\"\n${word.exampleTranslation}" } ?: ""
        binding.cardFront.visibility = View.VISIBLE; binding.cardFront.alpha = 1f
        binding.cardBack.visibility = View.GONE
        binding.layoutRating.visibility = View.GONE
        tts.speak(word.french)
    }

    private fun flipCard() {
        if (isFlipped) return; isFlipped = true
        binding.cardFront.animate().alpha(0f).setDuration(150).withEndAction {
            binding.cardFront.visibility = View.GONE
            binding.cardBack.visibility = View.VISIBLE
            binding.cardBack.alpha = 0f
            binding.cardBack.animate().alpha(1f).setDuration(150).start()
        }.start()
        binding.layoutRating.visibility = View.VISIBLE
        binding.tvHint.text = "How well did you know it?"
    }

    private fun rateCard(quality: Int) {
        val word = words[currentIndex]
        SpacedRepetitionEngine.processAnswer(word, quality)
        viewModel.updateWord(word)
        if (quality >= SpacedRepetitionEngine.QUALITY_CORRECT_SLOW) {
            sessionCorrect++
            xpEarned += if (quality == SpacedRepetitionEngine.QUALITY_CORRECT) 6 else 4
        }
        binding.cardBack.postDelayed({ showCard(currentIndex + 1) }, 300)
    }

    private fun endSession() {
        viewModel.onGameCompleted(GameResultEvent("flashcard", sessionCorrect, minOf(TOTAL, words.size), xpEarned))
        binding.tvFrench.text = "Session complete! 🎉"
        binding.tvPhonetic.text = "$xpEarned XP earned"
        binding.cardFront.visibility = View.VISIBLE; binding.cardFront.alpha = 1f
        binding.cardBack.visibility = View.GONE; binding.layoutRating.visibility = View.GONE
        binding.tvHint.text = "Cards that were Hard will come back sooner!"
        binding.cardFront.postDelayed({
            if (isAdded) findNavController().navigateUp()
        }, 2500)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

// ═══════════════════════════════════════════════
// BONUS ROUND
// ═══════════════════════════════════════════════
class BonusRoundFragment : Fragment() {

    private var _binding: FragmentBonusBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var tts: TTSManager

    private enum class BonusType { SPEED_ROUND, STREAK_SAVER, MYSTERY_BOX }
    private lateinit var currentBonus: BonusType

    private var words = mutableListOf<Word>()
    private var currentIndex = 0
    private var score = 0
    private var xpEarned = 0
    private var alive = true
    private var started = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBonusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TTSManager.getInstance(requireContext())
        currentBonus = BonusType.entries.random()
        showIntroScreen()
        // Bug 8 fix: disable the start button until words are loaded
        binding.btnStart.isEnabled = false
        viewModel.loadRandomWords("A1", 10) { loaded ->
            if (!isAdded) return@loadRandomWords
            words = loaded.toMutableList()
            binding.btnStart.isEnabled = true
        }
        binding.btnStart.setOnClickListener {
            if (!started) { started = true; binding.btnStart.visibility = View.GONE; startBonus() }
        }
    }

    private fun showIntroScreen() {
        binding.layoutChoices.visibility = View.GONE
        binding.tvQuestion.visibility = View.GONE
        binding.tvFeedback.text = ""
        binding.tvScore.text = "🎁 BONUS ROUND  •  2× XP"
        binding.tvScore.setTextColor(Color.parseColor("#FFE66D"))
        when (currentBonus) {
            BonusType.SPEED_ROUND -> { binding.tvTitle.text = "⚡ Speed Round!"; binding.tvSubtitle.text = "5 questions, 4 seconds each.\nAnswer fast for double XP!" }
            BonusType.STREAK_SAVER -> { binding.tvTitle.text = "💎 Streak Saver!"; binding.tvSubtitle.text = "Answer 5 questions in a row.\nOne wrong answer ends it!" }
            BonusType.MYSTERY_BOX -> { binding.tvTitle.text = "🎁 Mystery Box!"; binding.tvSubtitle.text = "5 words revealed letter by letter.\nGuess before the full word appears!" }
        }
        binding.btnStart.visibility = View.VISIBLE; binding.btnStart.text = "Let's go! →"
    }

    private fun startBonus() {
        binding.layoutChoices.visibility = View.VISIBLE; binding.tvQuestion.visibility = View.VISIBLE
        showBonusQuestion(0)
    }

    private fun showBonusQuestion(idx: Int) {
        if (idx >= 5 || idx >= words.size || (!alive && currentBonus == BonusType.STREAK_SAVER)) { endBonus(); return }
        currentIndex = idx
        val word = words[idx]
        binding.tvTitle.text = when (currentBonus) {
            BonusType.SPEED_ROUND -> "⚡ Question ${idx + 1}/5"
            BonusType.STREAK_SAVER -> "💎 Question ${idx + 1}/5"
            BonusType.MYSTERY_BOX -> "🎁 Mystery Word ${idx + 1}/5"
        }
        if (currentBonus == BonusType.MYSTERY_BOX) {
            val revealLen = minOf(2, word.french.length)
            binding.tvQuestion.text = word.french.take(revealLen) + "•".repeat((word.french.length - revealLen).coerceAtLeast(0))
        } else {
            binding.tvQuestion.text = word.french
        }
        tts.speak(word.french)
        buildBonusChoices(word, idx)
    }

    private fun buildBonusChoices(word: Word, idx: Int) {
        binding.layoutChoices.removeAllViews()
        val choices = mutableListOf(word.english to true)
        words.filter { it.id != word.id }.shuffled().take(3).forEach { choices.add(it.english to false) }
        choices.shuffle()
        for ((text, correct) in choices) {
            val btn = Button(requireContext()).apply {
                this.text = text; setTextColor(Color.parseColor("#EAEAEA"))
                setBackgroundColor(Color.parseColor("#1E3A5F")); textSize = 14f
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 8, 0, 8) }
                setOnClickListener { onBonusAnswer(this, correct, word, idx) }
            }
            binding.layoutChoices.addView(btn)
        }
    }

    private fun onBonusAnswer(btn: Button, correct: Boolean, word: Word, idx: Int) {
        for (i in 0 until binding.layoutChoices.childCount) binding.layoutChoices.getChildAt(i).isEnabled = false
        if (correct) {
            score++; xpEarned += 20
            btn.setBackgroundColor(Color.parseColor("#2ECC71"))
            binding.tvFeedback.text = "✓ Correct!  +20 XP  🎉"
            SpacedRepetitionEngine.processAnswer(word, SpacedRepetitionEngine.QUALITY_CORRECT)
        } else {
            btn.setBackgroundColor(Color.parseColor("#E74C3C"))
            binding.tvFeedback.text = "✗  ${word.french} = ${word.english}"
            SpacedRepetitionEngine.processAnswer(word, SpacedRepetitionEngine.QUALITY_WRONG)
            alive = false
        }
        viewModel.updateWord(word)
        btn.postDelayed({ showBonusQuestion(idx + 1) }, if (correct) 1000 else 1400)
    }

    private fun endBonus() {
        viewModel.onGameCompleted(GameResultEvent("bonus", score, 5, xpEarned))
        binding.tvTitle.text = "Bonus Complete! 🎁"
        binding.tvSubtitle.text = "$score/5 correct  •  +$xpEarned XP earned"
        binding.tvQuestion.text = if (xpEarned > 0) "🌟 Well done!" else "Better luck next time!"
        binding.tvFeedback.text = ""; binding.layoutChoices.removeAllViews()
        binding.btnStart.visibility = View.VISIBLE; binding.btnStart.text = "← Back to Home"
        binding.btnStart.setOnClickListener { findNavController().navigateUp() }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
