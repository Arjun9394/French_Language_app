package com.frenchquest.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale
import java.util.UUID

/** Passed from mini-games to GameViewModel after each session finishes. */
data class GameResultEvent(
    val gameType: String,
    val correct: Int,
    val total: Int,
    val xpEarned: Int,
    val durationMs: Long = 0L
) {
    val accuracy: Float
        get() = if (total == 0) 0f else correct.toFloat() / total
}

/**
 * Singleton wrapper around Android's TextToSpeech engine.
 * Language: Locale.FRENCH (fr_FR)
 */
class TTSManager private constructor(context: Context) {

    private var tts: TextToSpeech? = null
    private var isReady = false

    fun interface OnSpeakDoneListener {
        fun onDone()
    }

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.FRENCH) ?: TextToSpeech.LANG_NOT_SUPPORTED
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    Log.w(TAG, "French TTS not available, falling back to default.")
                    tts?.setLanguage(Locale.getDefault())
                }
                tts?.setSpeechRate(0.9f)
                isReady = true
                Log.d(TAG, "TTS ready.")
            } else {
                Log.e(TAG, "TTS initialization failed, status=$status")
            }
        }
    }

    /** Speak at normal (slightly slower) speed */
    fun speak(text: String) = speak(text, 0.9f, null)

    /** Speak at slow speed — good for A1 learners hearing a new word */
    fun speakSlow(text: String) = speak(text, 0.5f, null)

    /** Speak with callback when done */
    fun speak(text: String, rate: Float, listener: OnSpeakDoneListener?) {
        if (!isReady || text.isBlank()) return

        tts?.setSpeechRate(rate)
        val utteranceId = UUID.randomUUID().toString()

        if (listener != null) {
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(id: String?) {}
                override fun onDone(id: String?) {
                    if (id == utteranceId) listener.onDone()
                }
                override fun onError(id: String?) {}
            })
        }

        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun stop() {
        // Bug 9 fix: no-op if already shut down — stale fragment references call this safely
        if (!isReady) return
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        isReady = false
        instance = null
    }

    fun isReady(): Boolean = isReady

    companion object {
        private const val TAG = "TTSManager"

        @Volatile
        private var instance: TTSManager? = null

        fun getInstance(context: Context): TTSManager {
            return instance ?: synchronized(this) {
                instance ?: TTSManager(context).also { instance = it }
            }
        }
    }
}
