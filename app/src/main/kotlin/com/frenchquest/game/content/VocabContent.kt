package com.frenchquest.game.content

import com.frenchquest.data.models.Word

/**
 * All A1/A2 vocabulary content, hard-coded for offline-first.
 * 150+ words across 10 themed packs.
 */
object VocabContent {

    fun getAllWords(): List<Word> = listOf(
        getGreetings(), getNumbers(), getFood(), getFamily(), getTravel(),
        getDailyRoutine(), getWeather(), getShopping(), getHealth(), getSchool()
    ).flatten()

    private fun w(
        french: String, english: String, packId: String, cefrLevel: String,
        example: String? = null, exampleTrans: String? = null, phonetic: String? = null
    ) = Word(
        french = french, english = english, packId = packId, cefrLevel = cefrLevel,
        exampleSentence = example, exampleTranslation = exampleTrans, phonetic = phonetic
    )

    // ── Pack 1: Greetings & Basics (A1) ──────
    fun getGreetings(): List<Word> {
        val p = "greetings"; val l = "A1"
        return listOf(
            w("bonjour", "hello / good day", p, l, "Comment vous appelez-vous ?", "What is your name?", "bɔ̃.ʒuʁ"),
            w("bonsoir", "good evening", p, l, "Bonsoir, tout le monde !", "Good evening, everyone!", "bɔ̃.swaʁ"),
            w("bonne nuit", "good night", p, l, "Bonne nuit, à demain.", "Good night, see you tomorrow."),
            w("au revoir", "goodbye", p, l, "Au revoir et merci !", "Goodbye and thank you!", "o ʁə.vwaʁ"),
            w("salut", "hi / bye (informal)", p, l, "Salut, ça va ?", "Hi, how are you?", "sa.ly"),
            w("merci", "thank you", p, l, "Merci beaucoup !", "Thank you very much!", "mɛʁ.si"),
            w("s'il vous plaît", "please (formal)", p, l, "Un café, s'il vous plaît.", "A coffee, please."),
            w("s'il te plaît", "please (informal)", p, l, "Aide-moi, s'il te plaît.", "Help me, please."),
            w("de rien", "you're welcome", p, l, "— Merci ! — De rien.", "— Thank you! — You're welcome."),
            w("excusez-moi", "excuse me (formal)", p, l, "Excusez-moi, où est la gare ?", "Excuse me, where is the station?"),
            w("pardon", "sorry / pardon", p, l, "Pardon, je ne comprends pas.", "Sorry, I don't understand."),
            w("oui", "yes", p, l, "Oui, c'est correct.", "Yes, that's correct.", "wi"),
            w("non", "no", p, l, "Non, ce n'est pas ça.", "No, that's not it.", "nɔ̃"),
            w("je m'appelle", "my name is", p, l, "Je m'appelle Marie.", "My name is Marie."),
            w("comment ça va ?", "how are you?", p, l, "Comment ça va aujourd'hui ?", "How are you today?"),
            w("ça va bien", "I'm fine", p, l, "Ça va bien, merci.", "I'm fine, thank you."),
            w("je ne comprends pas", "I don't understand", p, l, "Désolé, je ne comprends pas.", "Sorry, I don't understand."),
            w("répétez, s'il vous plaît", "please repeat", p, l, "Répétez, s'il vous plaît !", "Please repeat!"),
            w("enchanté(e)", "nice to meet you", p, l, "Enchanté de vous rencontrer.", "Nice to meet you."),
            w("à bientôt", "see you soon", p, l, "À bientôt !", "See you soon!"),
        )
    }

    // ── Pack 2: Numbers & Time (A1) ───────────
    fun getNumbers(): List<Word> {
        val p = "numbers"; val l = "A1"
        return listOf(
            w("un", "one", p, l, "J'ai un chat.", "I have one cat.", "œ̃"),
            w("deux", "two", p, l, "Deux cafés, s'il vous plaît.", "Two coffees, please.", "dø"),
            w("trois", "three", p, l, "Il y a trois chambres.", "There are three rooms.", "tʁwa"),
            w("dix", "ten", p, l, "J'ai dix euros.", "I have ten euros.", "dis"),
            w("vingt", "twenty", p, l, "Il a vingt ans.", "He is twenty years old.", "vɛ̃"),
            w("cent", "one hundred", p, l, "Ça coûte cent euros.", "That costs one hundred euros.", "sɑ̃"),
            w("lundi", "Monday", p, l, "Lundi, je travaille.", "On Monday, I work."),
            w("mardi", "Tuesday", p, l, "Le cours est mardi.", "The class is on Tuesday."),
            w("janvier", "January", p, l, "Mon anniversaire est en janvier.", "My birthday is in January."),
            w("quelle heure est-il ?", "what time is it?", p, l, "Quelle heure est-il ? Il est midi.", "What time is it? It's noon."),
            w("aujourd'hui", "today", p, l, "Aujourd'hui, c'est vendredi.", "Today is Friday."),
            w("demain", "tomorrow", p, l, "Demain, je pars en voyage.", "Tomorrow, I leave on a trip."),
            w("hier", "yesterday", p, l, "Hier, j'ai vu un film.", "Yesterday, I saw a movie."),
            w("maintenant", "now", p, l, "Je veux manger maintenant.", "I want to eat now."),
            w("le matin", "the morning", p, l, "Je me lève le matin.", "I get up in the morning."),
        )
    }

    // ── Pack 3: Food & Drink (A1) ─────────────
    fun getFood(): List<Word> {
        val p = "food"; val l = "A1"
        return listOf(
            w("le pain", "bread", p, l, "Je mange du pain.", "I eat bread."),
            w("le fromage", "cheese", p, l, "Le fromage est délicieux.", "The cheese is delicious."),
            w("le café", "coffee", p, l, "Un café, s'il vous plaît.", "A coffee, please."),
            w("l'eau", "water", p, l, "Je voudrais de l'eau.", "I would like some water."),
            w("le vin", "wine", p, l, "Un verre de vin rouge.", "A glass of red wine."),
            w("la bière", "beer", p, l, "Une bière, s'il vous plaît.", "A beer, please."),
            w("le poulet", "chicken", p, l, "Je mange du poulet.", "I eat chicken."),
            w("le poisson", "fish", p, l, "Le poisson est frais.", "The fish is fresh."),
            w("la salade", "salad", p, l, "Une salade verte.", "A green salad."),
            w("le dessert", "dessert", p, l, "Je voudrais un dessert.", "I would like a dessert."),
            w("l'addition", "the bill", p, l, "L'addition, s'il vous plaît.", "The bill, please."),
            w("manger", "to eat", p, l, "Je veux manger.", "I want to eat.", "mɑ̃.ʒe"),
            w("boire", "to drink", p, l, "Je veux boire.", "I want to drink.", "bwaʁ"),
            w("le petit-déjeuner", "breakfast", p, l, "Le petit-déjeuner est à huit heures.", "Breakfast is at eight."),
            w("le dîner", "dinner", p, l, "Le dîner est prêt.", "Dinner is ready."),
        )
    }

    // ── Pack 4: Family & People (A1) ──────────
    fun getFamily(): List<Word> {
        val p = "family"; val l = "A1"
        return listOf(
            w("la mère", "mother", p, l, "Ma mère est gentille.", "My mother is kind."),
            w("le père", "father", p, l, "Mon père travaille.", "My father works."),
            w("le frère", "brother", p, l, "J'ai un frère.", "I have a brother."),
            w("la sœur", "sister", p, l, "Ma sœur est grande.", "My sister is tall."),
            w("les enfants", "children", p, l, "Les enfants jouent.", "The children play."),
            w("le fils", "son", p, l, "C'est mon fils.", "This is my son."),
            w("la fille", "daughter", p, l, "Ma fille a cinq ans.", "My daughter is five."),
            w("les parents", "parents", p, l, "Mes parents habitent à Paris.", "My parents live in Paris."),
            w("le mari", "husband", p, l, "Mon mari s'appelle Pierre.", "My husband's name is Pierre."),
            w("la femme", "wife / woman", p, l, "Ma femme est médecin.", "My wife is a doctor."),
            w("l'ami(e)", "friend", p, l, "C'est mon ami.", "This is my friend."),
            w("grand", "tall / big", p, l, "Il est grand.", "He is tall."),
            w("petit", "small / short", p, l, "Elle est petite.", "She is short."),
            w("jeune", "young", p, l, "Il est jeune.", "He is young."),
            w("vieux", "old", p, l, "Mon grand-père est vieux.", "My grandfather is old."),
        )
    }

    // ── Pack 5: Travel & Transport (A2) ───────
    fun getTravel(): List<Word> {
        val p = "travel"; val l = "A2"
        return listOf(
            w("la gare", "train station", p, l, "Où est la gare ?", "Where is the train station?"),
            w("l'aéroport", "airport", p, l, "L'avion part de l'aéroport.", "The plane leaves from the airport."),
            w("le billet", "ticket", p, l, "Un billet aller-retour.", "A round-trip ticket."),
            w("le train", "train", p, l, "Le train arrive à midi.", "The train arrives at noon."),
            w("le bus", "bus", p, l, "Je prends le bus.", "I take the bus."),
            w("le métro", "subway", p, l, "Le métro est rapide.", "The subway is fast."),
            w("l'hôtel", "hotel", p, l, "L'hôtel est confortable.", "The hotel is comfortable."),
            w("la chambre", "room", p, l, "Une chambre pour deux.", "A room for two."),
            w("tourner à gauche", "turn left", p, l, "Tournez à gauche.", "Turn left."),
            w("tourner à droite", "turn right", p, l, "Tournez à droite.", "Turn right."),
            w("tout droit", "straight ahead", p, l, "Allez tout droit.", "Go straight ahead."),
            w("le passeport", "passport", p, l, "Montrez votre passeport.", "Show your passport."),
            w("la valise", "suitcase", p, l, "Ma valise est lourde.", "My suitcase is heavy."),
            w("le voyage", "trip / journey", p, l, "Bon voyage !", "Have a good trip!"),
            w("la carte", "map / card", p, l, "J'ai besoin d'une carte.", "I need a map."),
        )
    }

    // ── Pack 6: Daily Routine (A1) ────────────
    fun getDailyRoutine(): List<Word> {
        val p = "daily_routine"; val l = "A1"
        return listOf(
            w("se réveiller", "to wake up", p, l, "Je me réveille à sept heures.", "I wake up at seven."),
            w("se lever", "to get up", p, l, "Je me lève tôt.", "I get up early."),
            w("se coucher", "to go to bed", p, l, "Je me couche à dix heures.", "I go to bed at ten."),
            w("dormir", "to sleep", p, l, "Je dors huit heures.", "I sleep eight hours.", "dɔʁ.miʁ"),
            w("travailler", "to work", p, l, "Je travaille le lundi.", "I work on Monday."),
            w("étudier", "to study", p, l, "J'étudie le français.", "I study French."),
            w("lire", "to read", p, l, "J'aime lire des livres.", "I like to read books.", "liʁ"),
            w("écrire", "to write", p, l, "J'écris une lettre.", "I write a letter."),
            w("regarder", "to watch", p, l, "Je regarde la télé.", "I watch TV."),
            w("écouter", "to listen", p, l, "J'écoute de la musique.", "I listen to music."),
            w("parler", "to speak", p, l, "Je parle français.", "I speak French.", "paʁ.le"),
            w("cuisiner", "to cook", p, l, "J'aime cuisiner le soir.", "I like to cook in the evening.", "kɥi.zi.ne"),
            w("prendre", "to take", p, l, "Je prends le bus.", "I take the bus."),
            w("aller", "to go", p, l, "Je vais à l'école.", "I go to school.", "a.le"),
            w("venir", "to come", p, l, "Tu viens avec moi ?", "Are you coming with me?"),
        )
    }

    // ── Pack 7: Weather & Seasons (A1) ────────
    fun getWeather(): List<Word> {
        val p = "weather"; val l = "A1"
        return listOf(
            w("il fait beau", "it's nice weather", p, l, "Il fait beau aujourd'hui.", "The weather is nice today."),
            w("il fait chaud", "it's hot", p, l, "Il fait chaud en été.", "It's hot in summer."),
            w("il fait froid", "it's cold", p, l, "Il fait froid en hiver.", "It's cold in winter."),
            w("il pleut", "it's raining", p, l, "Il pleut beaucoup.", "It's raining a lot."),
            w("il neige", "it's snowing", p, l, "Il neige en décembre.", "It snows in December."),
            w("le soleil", "the sun", p, l, "Le soleil brille.", "The sun is shining."),
            w("le nuage", "the cloud", p, l, "Il y a des nuages.", "There are clouds."),
            w("le vent", "the wind", p, l, "Le vent est fort.", "The wind is strong."),
            w("le printemps", "spring", p, l, "Le printemps est joli.", "Spring is pretty."),
            w("l'été", "summer", p, l, "J'adore l'été.", "I love summer."),
            w("l'automne", "autumn", p, l, "L'automne est coloré.", "Autumn is colorful."),
            w("l'hiver", "winter", p, l, "L'hiver est froid.", "Winter is cold."),
            w("la pluie", "rain", p, l, "La pluie tombe.", "The rain is falling."),
            w("le parapluie", "umbrella", p, l, "Prends ton parapluie.", "Take your umbrella."),
            w("le ciel", "the sky", p, l, "Le ciel est bleu.", "The sky is blue."),
        )
    }

    // ── Pack 8: Shopping (A2) ─────────────────
    fun getShopping(): List<Word> {
        val p = "shopping"; val l = "A2"
        return listOf(
            w("le magasin", "shop / store", p, l, "Le magasin est fermé.", "The shop is closed."),
            w("acheter", "to buy", p, l, "Je veux acheter un cadeau.", "I want to buy a gift."),
            w("combien", "how much", p, l, "Combien ça coûte ?", "How much does it cost?"),
            w("cher", "expensive", p, l, "C'est trop cher.", "It's too expensive."),
            w("bon marché", "cheap", p, l, "Ce livre est bon marché.", "This book is cheap."),
            w("la taille", "size", p, l, "Quelle est votre taille ?", "What is your size?"),
            w("le rouge", "red", p, l, "Je veux le rouge.", "I want the red one."),
            w("le bleu", "blue", p, l, "Le ciel est bleu.", "The sky is blue."),
            w("le vert", "green", p, l, "L'herbe est verte.", "The grass is green."),
            w("le noir", "black", p, l, "Un café noir.", "A black coffee."),
            w("le blanc", "white", p, l, "Le mur est blanc.", "The wall is white."),
            w("payer", "to pay", p, l, "Je peux payer par carte ?", "Can I pay by card?"),
            w("la robe", "dress", p, l, "Cette robe est jolie.", "This dress is pretty."),
            w("les chaussures", "shoes", p, l, "J'ai de nouvelles chaussures.", "I have new shoes."),
            w("le sac", "bag", p, l, "Mon sac est lourd.", "My bag is heavy."),
        )
    }

    // ── Pack 9: Health & Body (A2) ────────────
    fun getHealth(): List<Word> {
        val p = "health"; val l = "A2"
        return listOf(
            w("la tête", "head", p, l, "J'ai mal à la tête.", "I have a headache."),
            w("le ventre", "stomach", p, l, "J'ai mal au ventre.", "I have a stomachache."),
            w("le bras", "arm", p, l, "J'ai mal au bras.", "My arm hurts."),
            w("la jambe", "leg", p, l, "J'ai mal à la jambe.", "My leg hurts."),
            w("le dos", "back", p, l, "J'ai mal au dos.", "My back hurts."),
            w("le médecin", "doctor", p, l, "Je vais chez le médecin.", "I'm going to the doctor."),
            w("la pharmacie", "pharmacy", p, l, "Où est la pharmacie ?", "Where is the pharmacy?"),
            w("le médicament", "medicine", p, l, "Prenez ce médicament.", "Take this medicine."),
            w("je suis malade", "I am sick", p, l, "Je suis malade aujourd'hui.", "I am sick today."),
            w("la fièvre", "fever", p, l, "J'ai de la fièvre.", "I have a fever."),
            w("fatigué(e)", "tired", p, l, "Je suis très fatigué.", "I am very tired."),
            w("l'hôpital", "hospital", p, l, "L'hôpital est loin.", "The hospital is far."),
            w("le dentiste", "dentist", p, l, "J'ai rendez-vous chez le dentiste.", "I have a dentist appointment."),
            w("les yeux", "eyes", p, l, "Ses yeux sont bleus.", "Her eyes are blue."),
            w("la bouche", "mouth", p, l, "Ouvrez la bouche.", "Open your mouth."),
        )
    }

    // ── Pack 10: School & Studies (A1) ────────
    fun getSchool(): List<Word> {
        val p = "school"; val l = "A1"
        return listOf(
            w("l'école", "school", p, l, "Je vais à l'école.", "I go to school."),
            w("le professeur", "teacher", p, l, "Le professeur est sympa.", "The teacher is nice."),
            w("l'élève", "student", p, l, "L'élève étudie.", "The student studies."),
            w("le livre", "book", p, l, "J'ai un nouveau livre.", "I have a new book."),
            w("le cahier", "notebook", p, l, "Écris dans ton cahier.", "Write in your notebook."),
            w("le stylo", "pen", p, l, "J'ai besoin d'un stylo.", "I need a pen."),
            w("la classe", "classroom", p, l, "La classe est grande.", "The classroom is big."),
            w("les devoirs", "homework", p, l, "J'ai fini mes devoirs.", "I finished my homework."),
            w("l'examen", "exam", p, l, "L'examen est demain.", "The exam is tomorrow."),
            w("apprendre", "to learn", p, l, "J'apprends le français.", "I learn French."),
            w("comprendre", "to understand", p, l, "Je comprends.", "I understand."),
            w("la question", "question", p, l, "J'ai une question.", "I have a question."),
            w("la réponse", "answer", p, l, "La réponse est correcte.", "The answer is correct."),
            w("difficile", "difficult", p, l, "C'est difficile.", "It's difficult."),
            w("facile", "easy", p, l, "C'est facile !", "It's easy!"),
        )
    }
}
