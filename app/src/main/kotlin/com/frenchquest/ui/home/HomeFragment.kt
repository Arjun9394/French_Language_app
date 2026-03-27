package com.frenchquest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.frenchquest.R
import com.frenchquest.data.models.UserProgress
import com.frenchquest.databinding.FragmentHomeBinding
import com.frenchquest.ui.game.GameViewModel
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()

    data class GameMode(
        val emoji: String, val title: String,
        val subtitle: String, val navigationAction: Int
    )

    companion object {
        val GAME_MODES = listOf(
            GameMode("🃏", "Word Match", "Match French ↔ English", R.id.action_home_to_wordMatch),
            GameMode("📖", "Flashcards", "Flip & remember", R.id.action_home_to_flashcard),
            GameMode("✏️", "Fill the Gap", "Complete the sentence", R.id.action_home_to_fillGap),
            GameMode("🎧", "Listen & Tap", "Hear it, tap it", R.id.action_home_to_listenTap),
            GameMode("💬", "Dialogue", "Choose your reply", R.id.action_home_to_dialogue),
            GameMode("🎤", "Pronounce", "Say it right", R.id.action_home_to_pronounce),
            GameMode("⚡", "Quiz Blitz", "10 questions, fast!", R.id.action_home_to_quizBlitz),
            GameMode("🎁", "Bonus Round", "Surprise! Double XP", R.id.action_home_to_bonus),
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProgress().observe(viewLifecycleOwner, ::updateUI)
        setupGameGrid()
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_wordMatch)
        }
    }

    private fun updateUI(p: UserProgress?) {
        if (p == null) return

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        binding.tvGreeting.text = when {
            hour < 12 -> "Bonjour ! 🌅"
            hour < 18 -> "Bon après-midi ! ☀️"
            else -> "Bonsoir ! 🌙"
        }

        binding.tvStreakCount.text = "${p.currentStreak} 🔥"
        binding.tvLevel.text = "Level ${p.level}  •  ${p.cefrLevel}"
        binding.tvXP.text = "${p.totalXP} XP"

        binding.pbXP.max = p.getXPRangeForLevel().coerceAtLeast(1)
        binding.pbXP.progress = p.getXPProgressInLevel()

        binding.pbDailyXP.max = p.dailyXPGoal
        binding.pbDailyXP.progress = p.dailyXPToday.coerceAtMost(p.dailyXPGoal)
        binding.tvDailyXP.text = "${p.dailyXPToday.coerceAtMost(p.dailyXPGoal)} / ${p.dailyXPGoal} XP today"

        binding.pbDailyWords.max = p.dailyWordsGoal
        binding.pbDailyWords.progress = p.dailyWordsToday.coerceAtMost(p.dailyWordsGoal)
        binding.tvDailyWords.text = "${p.dailyWordsToday.coerceAtMost(p.dailyWordsGoal)} / ${p.dailyWordsGoal} words"

        val gamesLeft = (7 - p.weeklyGamesPlayed).coerceAtLeast(0)
        binding.tvWeeklyChallenge.text = if (gamesLeft == 0)
            "📅 Weekly challenge complete! 🏆"
        else
            "📅 Play $gamesLeft more games this week"
    }

    private fun setupGameGrid() {
        binding.rvGameGrid.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvGameGrid.adapter = GameModeAdapter(GAME_MODES) { mode ->
            try { findNavController().navigate(mode.navigationAction) }
            catch (_: Exception) { }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
