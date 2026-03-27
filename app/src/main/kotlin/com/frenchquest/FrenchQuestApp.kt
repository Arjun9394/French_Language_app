package com.frenchquest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

class FrenchQuestApp : Application() {

    companion object {
        const val NOTIF_CHANNEL_ID = "frenchquest_reminders"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        scheduleStreakReminder()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIF_CHANNEL_ID,
                "FrenchQuest Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Daily French learning reminders"
            }
            getSystemService(NotificationManager::class.java)
                ?.createNotificationChannel(channel)
        }
    }

    private fun scheduleStreakReminder() {
        val reminderWork = PeriodicWorkRequestBuilder<StreakReminderWorker>(
            24, TimeUnit.HOURS
        )
            .setInitialDelay(calculateDelayToEightPm(), TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "streak_reminder",
            ExistingPeriodicWorkPolicy.KEEP,
            reminderWork
        )
    }

    private fun calculateDelayToEightPm(): Long {
        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            if (before(now)) add(Calendar.DAY_OF_YEAR, 1)
        }
        return target.timeInMillis - now.timeInMillis
    }
}
