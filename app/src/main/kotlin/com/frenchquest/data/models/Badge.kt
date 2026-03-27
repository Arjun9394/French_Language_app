package com.frenchquest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "badges")
data class Badge(
    @PrimaryKey
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val iconResName: String = "",
    var isUnlocked: Boolean = false,
    var unlockedAt: Long = 0L
) {
    companion object {
        const val FIRST_WORD = "first_word"
        const val STREAK_3 = "streak_3"
        const val STREAK_7 = "streak_7"
        const val STREAK_30 = "streak_30"
        const val WORDS_10 = "words_10"
        const val WORDS_50 = "words_50"
        const val WORDS_100 = "words_100"
        const val LEVEL_5 = "level_5"
        const val LEVEL_10 = "level_10"
        const val PERFECT_QUIZ = "perfect_quiz"
        const val DIALOGUE_MASTER = "dialogue_master"
        const val LISTENING_ACE = "listening_ace"
        const val SPEAKING_STAR = "speaking_star"
        const val BONUS_ROUND = "bonus_round"
        const val WEEKLY_CHAMPION = "weekly_champion"
        const val EARLY_BIRD = "early_bird"
        const val NIGHT_OWL = "night_owl"
        const val SPEED_DEMON = "speed_demon"
        const val COMEBACK_KID = "comeback_kid"
        const val A2_UNLOCKED = "a2_unlocked"

        fun getDefaultBadges(): List<Badge> = listOf(
            Badge(FIRST_WORD, "Premier Mot!", "Learn your very first French word", "badge_star"),
            Badge(STREAK_3, "3 Jours!", "Maintain a 3-day learning streak", "badge_flame_3"),
            Badge(STREAK_7, "Une Semaine!", "Maintain a 7-day streak", "badge_flame_7"),
            Badge(STREAK_30, "Trente Jours!", "30-day streak — incredible dedication", "badge_flame_30"),
            Badge(WORDS_10, "10 Mots", "Learn 10 French words", "badge_book"),
            Badge(WORDS_50, "50 Mots", "Learn 50 French words", "badge_book_gold"),
            Badge(WORDS_100, "Centenaire!", "Learn 100 French words", "badge_trophy"),
            Badge(LEVEL_5, "Niveau 5", "Reach level 5", "badge_level5"),
            Badge(LEVEL_10, "Niveau 10", "Reach level 10", "badge_level10"),
            Badge(PERFECT_QUIZ, "Quiz Parfait!", "Score 100% on a quiz", "badge_perfect"),
            Badge(DIALOGUE_MASTER, "Maître Dialogue", "Complete 10 dialogue mini-games", "badge_chat"),
            Badge(LISTENING_ACE, "As de l'Écoute", "80%+ listening accuracy over 10 games", "badge_headphones"),
            Badge(SPEAKING_STAR, "Étoile de Parole", "Pronounce 20 words correctly", "badge_mic"),
            Badge(BONUS_ROUND, "Bonus Décroché!", "Complete your first bonus round", "badge_bonus"),
            Badge(WEEKLY_CHAMPION, "Champion Semaine", "Complete a full weekly challenge", "badge_crown"),
            Badge(EARLY_BIRD, "Lève-tôt", "Study before 8:00 AM", "badge_sun"),
            Badge(NIGHT_OWL, "Oiseau de Nuit", "Study after 10:00 PM", "badge_moon"),
            Badge(SPEED_DEMON, "Fulgurant!", "Finish a quiz in under 30 seconds", "badge_lightning"),
            Badge(COMEBACK_KID, "Revanche!", "Answer 5 correct after 3 wrong in a row", "badge_comeback"),
            Badge(A2_UNLOCKED, "Niveau A2!", "Graduate to A2 content", "badge_a2"),
        )
    }
}
