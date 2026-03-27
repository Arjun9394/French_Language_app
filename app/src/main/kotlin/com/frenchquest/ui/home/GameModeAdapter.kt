package com.frenchquest.ui.home

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameModeAdapter(
    private val modes: List<HomeFragment.GameMode>,
    private val onSelected: (HomeFragment.GameMode) -> Unit
) : RecyclerView.Adapter<GameModeAdapter.ViewHolder>() {

    companion object {
        private val CARD_COLORS = arrayOf(
            "#FF6B35", "#4ECDC4", "#A29BFE", "#00CEC9",
            "#FDCB6E", "#E17055", "#74B9FF", "#55EFC4"
        )
    }

    class ViewHolder(
        val root: LinearLayout,
        val tvEmoji: TextView,
        val tvTitle: TextView,
        val tvSubtitle: TextView
    ) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ctx = parent.context

        val card = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(16, 16, 16, 16) }
        }

        val tvEmoji = TextView(ctx).apply {
            textSize = 36f
            gravity = Gravity.CENTER
        }
        val tvTitle = TextView(ctx).apply {
            textSize = 15f
            setTextColor(Color.parseColor("#EAEAEA"))
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        val tvSubtitle = TextView(ctx).apply {
            textSize = 11f
            setTextColor(Color.parseColor("#A0A8B0"))
            gravity = Gravity.CENTER
        }

        card.addView(tvEmoji)
        card.addView(tvTitle)
        card.addView(tvSubtitle)

        return ViewHolder(card, tvEmoji, tvTitle, tvSubtitle)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mode = modes[position]
        holder.tvEmoji.text = mode.emoji
        holder.tvTitle.text = mode.title
        holder.tvSubtitle.text = mode.subtitle

        val bg = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 24f
            setColor(Color.parseColor("#1E3A5F"))
            setStroke(4, Color.parseColor(CARD_COLORS[position % CARD_COLORS.size]))
        }
        holder.root.background = bg

        holder.root.setOnClickListener { v ->
            v.animate().scaleX(0.94f).scaleY(0.94f).setDuration(80)
                .withEndAction {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(80).start()
                    onSelected(mode)
                }.start()
        }
    }

    override fun getItemCount() = modes.size
}
