package com.frenchquest.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.frenchquest.R
import com.frenchquest.data.database.FrenchDatabase
import com.frenchquest.databinding.FragmentSplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivLogo.alpha = 0f
        binding.ivLogo.animate().alpha(1f).setDuration(600).start()
        binding.tvTagline.alpha = 0f
        binding.tvTagline.animate().alpha(1f).setStartDelay(400).setDuration(500).start()

        viewLifecycleOwner.lifecycleScope.launch {
            kotlinx.coroutines.delay(1500)
            if (!isAdded) return@launch
            val needsPlacement = withContext(Dispatchers.IO) {
                val db = FrenchDatabase.getInstance(requireContext())
                val p = db.userProgressDao().getProgressSync()
                p == null || !p.hasCompletedPlacement
            }
            if (!isAdded) return@launch
            val action = if (needsPlacement)
                R.id.action_splash_to_placement
            else
                R.id.action_splash_to_home
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
