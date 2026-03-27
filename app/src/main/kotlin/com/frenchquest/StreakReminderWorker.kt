package com.frenchquest

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.frenchquest.data.database.FrenchDatabase
import com.frenchquest.ui.MainActivity

/**
 * Fires a local notification at ~20:00 if the user hasn't played today.
 * Uses CoroutineWorker for modern async support.
 */
class StreakReminderWorker(
    private val ctx: Context,
    params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        val db = FrenchDatabase.getInstance(applicationContext)
        val p = db.userProgressDao().getProgressSync() ?: return Result.success()

        val todayDay = System.currentTimeMillis() / 86_400_000L
        val lastDay = p.lastPlayedDate / 86_400_000L

        if (lastDay < todayDay) {
            val msg = when {
                p.currentStreak >= 7 -> "🔥 ${p.currentStreak} jours de suite ! Ne brise pas ta série !"
                p.currentStreak >= 1 -> "Série en cours : ${p.currentStreak} jours 🇫🇷 Joue vite !"
                else -> "Bonjour ! Ton français t'attend. 5 minutes ? 🇫🇷"
            }
            sendNotification("FrenchQuest 🇫🇷", msg)
        }

        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        val intent = Intent(ctx, MainActivity::class.java)
        val pi = PendingIntent.getActivity(
            ctx, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(ctx, FrenchQuestApp.NOTIF_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val nm = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        nm?.notify(42, builder.build())
    }
}
