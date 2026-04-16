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
import com.frenchquest.databinding.FragmentHomeBinding
import com.frenchquest.ui.game.GameViewModel

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
            GameMode("⚡", "Quiz Blitz", "Fast review", R.id.action_home_to_quizBlitz)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.tvGreeting.text = "Survival French"
        
        // Hide context elements
        binding.tvStreakCount.visibility = View.GONE
        binding.tvLevel.visibility = View.GONE
        binding.tvXP.visibility = View.GONE
        binding.pbXP.visibility = View.GONE
        binding.pbDailyXP.visibility = View.GONE
        binding.tvDailyXP.visibility = View.GONE
        binding.pbDailyWords.visibility = View.GONE
        binding.tvDailyWords.visibility = View.GONE
        binding.tvWeeklyChallenge.visibility = View.GONE
        
        setupGameGrid()
        
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_wordMatch)
        }
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
