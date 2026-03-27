package com.frenchquest.ui.progress

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frenchquest.R
import com.frenchquest.data.database.FrenchDatabase
import com.frenchquest.data.models.Badge
import com.frenchquest.data.models.UserProgress
import com.frenchquest.databinding.FragmentProgressBinding
import com.frenchquest.ui.game.GameViewModel

class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private val badgeAdapter = BadgeAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBadges.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvBadges.adapter = badgeAdapter

        viewModel.getProgress().observe(viewLifecycleOwner, ::bindProgress)

        FrenchDatabase.getInstance(requireContext())
            .badgeDao().getAllBadges()
            .observe(viewLifecycleOwner) { badgeAdapter.submitList(it) }
    }

    private fun bindProgress(p: UserProgress?) {
        if (p == null) return

        binding.tvLevel.text = "Niveau ${p.level} — ${p.cefrLevel}"
        binding.tvCefrLevel.text = "CEFR: ${p.cefrLevel}"
        val xpInLevel = p.getXPProgressInLevel()
        val xpRange = p.getXPRangeForLevel()
        binding.tvXP.text = "${p.totalXP} XP total  |  $xpInLevel/$xpRange pour niveau ${p.level + 1}"
        binding.pbXP.max = xpRange; binding.pbXP.progress = xpInLevel

        binding.tvStreak.text = "🔥 Série actuelle : ${p.currentStreak} jours\n🏆 Record : ${p.longestStreak} jours"
        binding.tvWordsLearned.text = "📚 ${p.wordsLearned} mots appris"

        val diffLabel = when (p.difficultyLevel) {
            1 -> "⭐ Très facile"; 2 -> "⭐⭐ Facile"; 3 -> "⭐⭐⭐ Moyen"
            4 -> "⭐⭐⭐⭐ Difficile"; 5 -> "⭐⭐⭐⭐⭐ Expert"
            else -> "Niveau ${p.difficultyLevel}"
        }
        binding.tvDifficulty.text = "Difficulté adaptive : $diffLabel"

        bindSkillBar(binding.pbListening, binding.tvListeningPct, p.listeningAccuracy, "🎧 Écoute")
        bindSkillBar(binding.pbSpeaking, binding.tvSpeakingPct, p.speakingAccuracy, "🎤 Parole")
        bindSkillBar(binding.pbReading, binding.tvReadingPct, p.readingAccuracy, "📖 Lecture")
        bindSkillBar(binding.pbWriting, binding.tvWritingPct, p.writingAccuracy, "✏️ Écriture")
    }

    private fun bindSkillBar(pb: ProgressBar, tv: TextView, accuracy: Float, label: String) {
        val pct = (accuracy * 100).toInt()
        pb.max = 100; pb.progress = pct
        tv.text = "$label : $pct%"
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

// ── Badge Adapter ─────────────────────────────
class BadgeAdapter : ListAdapter<Badge, BadgeAdapter.BVH>(
    object : DiffUtil.ItemCallback<Badge>() {
        override fun areItemsTheSame(a: Badge, b: Badge) = a.id == b.id
        override fun areContentsTheSame(a: Badge, b: Badge) = a.isUnlocked == b.isUnlocked
    }
) {
    class BVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvEmoji: TextView = view.findViewById(R.id.tvBadgeEmoji)
        val tvTitle: TextView = view.findViewById(R.id.tvBadgeTitle)
        val tvDesc: TextView = view.findViewById(R.id.tvBadgeDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_badge, parent, false)
        return BVH(v)
    }

    override fun onBindViewHolder(holder: BVH, position: Int) {
        val badge = getItem(position)
        holder.tvEmoji.text = if (badge.isUnlocked) "🏅" else "🔒"
        holder.tvTitle.text = badge.title
        holder.tvDesc.text = badge.description
        holder.itemView.alpha = if (badge.isUnlocked) 1f else 0.4f
    }
}
