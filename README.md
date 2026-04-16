# 🇫🇷 Survival French — Essential Phrases Game

**Note: This application is designed exclusively for Android phones.**

A streamlined, offline-first Android application focused 100% on mastering essential **Survival French** sentences. 
Rebuilt to focus on the core phrases needed for travel and immediate communication.

---

## 📱 Platform Compatibility
*   **Operating System**: Android Only (API 26+)
*   **Device Type**: Android Smartphones
*   **Offline Mode**: Fully supported (No internet required)

---

## 🚀 Survival Curriculum
The app has been overhauled to focus exclusively on the **39 Essential Survival Sentences**, including:
- 🔴 **Survival Essentials** (Greetings and fundamental politeness)
- 🚻 **Getting Around** (Directions and transport)
- ☕ **Daily Life** (Café, shopping, and hotel interactions)

---

## 🎮 Core Games (Optimized for Survival)
The following modes are optimized for sentence-length learning:
- 🃏 **Word Match**: Match full French phrases to their English meanings.
- 📖 **Flashcards**: Standard spaced-repetition review.
- ✏️ **Fill the Gap**: Focuses on completing survival sentences.
- 🎧 **Listen & Tap**: Tap words in order as you hear them.
- ⚡ **Quiz Blitz**: Fast-paced multiple-choice review.

---

## 🧠 Clean Design & Logic
- **Adaptive Difficulty**: Hidden behind the scenes to keep the UI clean.
- **Zero Distraction**: No XP bars, levels, or streaks in the main view.
- **Privacy First**: No telemetry or keys stored in the public repository.

---

## 🔧 Setup (Android Studio)
### Prerequisites
- Android Studio Ladybug or newer
- Android SDK 35
- JDK 17

### Steps
1. Open Android Studio → **File → Open** → select `FrenchQuestKt/`
2. Wait for Gradle sync (Kotlin DSL + KSP)
3. Run on device/emulator (API 26+)

---

## ✅ Major Progress Updates
- ✅ Stripped application down to **Core Survival Sentences**.
- ✅ Removed all "gamification" context (Streaks/Levels/XP) for a focused learning experience.
- ✅ Cleaned Git history of sensitive keys and build artifacts.
- ✅ Optimized Dialogue mini-games for raw sentence practice.
- ✅ Optimized README for clarity on platform and purpose.
- ✅ Fixed `ListenTapGameFragment` duplicate `onViewCreated`
- ✅ Removed brace-expanded junk directories (`{ui/{home,game,...}}`)
- ✅ Added proper navigation graph with all 8 game destinations
- ✅ Material Design 3 theming
- ✅ All fragments use `activityViewModels()` delegate
- ✅ Proper `onDestroyView` cleanup with nullable binding pattern
