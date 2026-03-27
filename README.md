# 🇫🇷 FrenchQuest — A1/A2 French Learning Game

A fully offline-first Android game for learning French at CEFR A1–A2 level.
Built with **Kotlin**, **Room + KSP**, **Coroutines**, **ViewBinding**, **Navigation Component**, and **Material Design 3**.

---

## 🚀 Tech Stack (Modern Android 2025)

| Technology | Version | Purpose |
|---|---|---|
| Kotlin | 2.1.0 | Primary language (no Java) |
| AGP | 8.7.3 | Android Gradle Plugin |
| Gradle | 8.11.1 | Build system (Kotlin DSL) |
| compileSdk | 35 | Latest Android SDK |
| Room + KSP | 2.6.1 / 2.1.0 | Database with Kotlin Symbol Processing |
| Coroutines | 1.9.0 | Async operations (no raw executors) |
| ViewBinding | native | Type-safe view access |
| Navigation | 2.8.5 | Single-activity architecture |
| Material 3 | 1.12.0 | Modern theming |
| WorkManager | 2.10.0 | Background streak reminders |
| Lottie | 6.6.2 | Animations |
| Lifecycle | 2.8.7 | ViewModel + LiveData |

---

## 📁 Project Structure

```
FrenchQuestKt/
├── build.gradle.kts                    # Root (Kotlin DSL)
├── settings.gradle.kts                 # Module config
├── gradle.properties
├── app/
│   ├── build.gradle.kts                # App-level deps (KSP, coroutines)
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── kotlin/com/frenchquest/
│       │   ├── FrenchQuestApp.kt       # Application class
│       │   ├── StreakReminderWorker.kt  # CoroutineWorker
│       │   ├── data/
│       │   │   ├── database/           # Room DB, DAOs (separate files)
│       │   │   ├── models/             # Word, UserProgress, Badge
│       │   │   └── repository/         # FrenchQuestRepository
│       │   ├── game/
│       │   │   ├── engines/            # SpacedRepetition, AdaptiveDifficulty
│       │   │   └── content/            # VocabContent, DialogueContent
│       │   ├── ui/
│       │   │   ├── MainActivity.kt
│       │   │   ├── onboarding/         # Splash, PlacementTest
│       │   │   ├── home/               # Dashboard, GameModeAdapter
│       │   │   ├── game/               # All 8 mini-game fragments + ViewModel
│       │   │   └── progress/           # Stats, badges
│       │   └── utils/                  # TTSManager, GameResultEvent
│       └── res/
│           ├── layout/                 # All XML layouts
│           ├── navigation/             # nav_graph.xml
│           ├── values/                 # colors, strings, themes
│           ├── menu/                   # bottom_nav_menu
│           ├── drawable/               # Vector icons
│           └── anim/                   # Transitions
```

---

## 🎮 Mini-Games (8 total, all implemented)

| Game | Fragment | Skills |
|---|---|---|
| Word Match | `WordMatchGameFragment` | Vocabulary |
| Flashcards | `FlashcardGameFragment` | Vocabulary + SR |
| Fill the Gap | `FillGapGameFragment` | Grammar |
| Listen & Tap | `ListenTapGameFragment` | Listening |
| Dialogue | `DialogueGameFragment` | Speaking/Reading |
| Pronounce | `PronounceGameFragment` | Speaking |
| Quiz Blitz | `QuizBlitzFragment` | Mixed |
| Bonus Round | `BonusRoundFragment` | Engagement |

---

## 🧠 Core Systems

### Spaced Repetition (SM-2)
- Each word tracks `easeFactor`, `interval`, `nextReviewDate`
- Correct → increase interval; Wrong → reset to 1 day
- Flashcard game loads due words first

### Adaptive Difficulty (5 levels)
- Tracks rolling 10-game accuracy
- >80% for 3 sessions → difficulty up
- <40% for 2 sessions → difficulty down
- Controls: choice count, time limits, CEFR level, hints

### Gamification
- XP per game with difficulty multiplier
- Daily streak with WorkManager notifications
- 20 badges with automatic unlock checks
- Weekly challenges

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
4. First launch → placement test → home screen

---

## ✅ Issues Fixed from Original

- ✅ Converted entire codebase from Java to Kotlin
- ✅ Kotlin DSL (`build.gradle.kts`) instead of Groovy
- ✅ Added `settings.gradle.kts` (was missing)
- ✅ KSP instead of `annotationProcessor` for Room
- ✅ Coroutines instead of raw `ExecutorService`
- ✅ `CoroutineWorker` instead of `Worker`
- ✅ ViewBinding instead of `findViewById`
- ✅ Removed `SplashActivity` (manifest referenced it but it didn't exist) — now uses `SplashFragment`
- ✅ Added missing `data/repository/` layer
- ✅ Split `UserProgressDao` and `BadgeDao` into separate files
- ✅ Removed duplicate `BonusAndFlashcardFragments.java` (had duplicate class defs + dummy `CardView`)
- ✅ Fixed `ListenTapGameFragment` duplicate `onViewCreated`
- ✅ Removed brace-expanded junk directories (`{ui/{home,game,...}}`)
- ✅ Added proper navigation graph with all 8 game destinations
- ✅ Material Design 3 theming
- ✅ All fragments use `activityViewModels()` delegate
- ✅ Proper `onDestroyView` cleanup with nullable binding pattern
