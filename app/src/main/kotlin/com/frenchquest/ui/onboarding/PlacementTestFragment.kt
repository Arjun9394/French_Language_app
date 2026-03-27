package com.frenchquest.ui.onboarding

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
import com.frenchquest.R
import com.frenchquest.databinding.FragmentPlacementTestBinding
import com.frenchquest.ui.game.GameViewModel
import java.util.Collections

class PlacementTestFragment : Fragment() {

    private var _binding: FragmentPlacementTestBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()

    private var currentIndex = 0
    private var score = 0

    companion object {
        private val QUESTIONS = arrayOf(
            arrayOf("What does 'bonjour' mean?", "hello / good day", "good night", "thank you", "goodbye", "'Bonjour' is the standard daytime greeting."),
            arrayOf("Translate: 'Je m'appelle Marie.'", "My name is Marie.", "I eat Marie.", "I like Marie.", "I am Marie's friend.", "'Je m'appelle' = my name is."),
            arrayOf("What does 'merci beaucoup' mean?", "thank you very much", "excuse me a lot", "good evening", "sorry so much", "'Merci' = thank you, 'beaucoup' = a lot."),
            arrayOf("Choose: 'I am hungry.'", "J'ai faim.", "Je suis faim.", "J'ai chaud.", "Je fais faim.", "In French: 'avoir faim' — to have hunger."),
            arrayOf("'Où est la gare ?' means:", "Where is the station?", "Where is the shop?", "What time is the train?", "How far is the station?", "'Où est' = where is, 'la gare' = the train station."),
            arrayOf("Translate: 'Il fait beau aujourd'hui.'", "The weather is nice today.", "He is beautiful today.", "It rains today.", "Today is Monday.", "'Il fait beau' is the set phrase for nice weather."),
            arrayOf("Which word means 'to drink'?", "boire", "manger", "dormir", "lire", "'Boire' = to drink."),
            arrayOf("Complete: 'Je ___ du café.'", "bois", "mange", "lis", "dors", "'Je bois' = I drink — first person of 'boire'."),
            arrayOf("'L'addition' in a restaurant means:", "the bill", "the menu", "the starter", "the waiter", "'L'addition, s'il vous plaît' = The bill, please!"),
            arrayOf("Translate: 'Pouvez-vous répéter, s'il vous plaît ?'", "Can you repeat, please?", "Can you help me?", "Do you speak English?", "Where is the exit?", "'Répéter' = to repeat."),
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPlacementTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pbProgress.max = QUESTIONS.size
        showQuestion(0)
    }

    private fun showQuestion(idx: Int) {
        if (idx >= QUESTIONS.size) { finishTest(); return }
        currentIndex = idx
        val q = QUESTIONS[idx]
        binding.pbProgress.progress = idx
        binding.tvProgress.text = "${idx + 1} / ${QUESTIONS.size}"
        binding.tvQuestion.text = q[0]
        binding.tvFeedback.text = ""

        val choices = mutableListOf(
            q[1] to true, q[2] to false, q[3] to false, q[4] to false
        ).also { Collections.shuffle(it) }

        binding.layoutChoices.removeAllViews()
        for ((text, isCorrect) in choices) {
            val btn = Button(requireContext()).apply {
                this.text = text
                setTextColor(Color.parseColor("#EAEAEA"))
                setBackgroundColor(Color.parseColor("#1E3A5F"))
                textSize = 14f
                setPadding(16, 20, 16, 20)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 8, 0, 8) }
                setOnClickListener { onAnswer(this, isCorrect, q[5]) }
            }
            binding.layoutChoices.addView(btn)
        }
    }

    private fun onAnswer(btn: Button, isCorrect: Boolean, explanation: String) {
        for (i in 0 until binding.layoutChoices.childCount)
            binding.layoutChoices.getChildAt(i).isEnabled = false

        if (isCorrect) {
            score++
            btn.setBackgroundColor(Color.parseColor("#2ECC71"))
            binding.tvFeedback.setTextColor(Color.parseColor("#2ECC71"))
            binding.tvFeedback.text = "✓ Correct!\n💡 $explanation"
        } else {
            btn.setBackgroundColor(Color.parseColor("#E74C3C"))
            binding.tvFeedback.setTextColor(Color.parseColor("#E74C3C"))
            binding.tvFeedback.text = "✗ Not quite.\n💡 $explanation"
        }
        btn.postDelayed({ showQuestion(currentIndex + 1) }, 1600)
    }

    private fun finishTest() {
        val level = if (score >= 6) "A2" else "A1"
        viewModel.setPlacementResult(level, score)
        binding.tvQuestion.text = "Test complete! 🎉"
        binding.tvFeedback.setTextColor(Color.parseColor("#FFE66D"))
        binding.tvFeedback.text = buildString {
            append("Your score: $score/10\nStarting level: $level\n\n")
            if (level == "A2") append("Impressive! You know some French already. You'll start with A2 content.")
            else append("Great starting point! You'll build from A1 core vocabulary.")
        }
        binding.layoutChoices.removeAllViews()
        binding.pbProgress.progress = QUESTIONS.size
        binding.tvFeedback.postDelayed({
            if (isAdded) findNavController().navigate(R.id.action_placement_to_home)
        }, 3000)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
