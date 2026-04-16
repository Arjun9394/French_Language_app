package com.frenchquest.game.content

import com.frenchquest.data.models.Word

/**
 * Survival vocabulary content based on user reference.
 */
object VocabContent {

    fun getAllWords(): List<Word> = getSurvival()

    private fun w(
        french: String, english: String, packId: String, cefrLevel: String,
        example: String? = null, exampleTrans: String? = null, phonetic: String? = null
    ) = Word(
        french = french, english = english, packId = packId, cefrLevel = cefrLevel,
        exampleSentence = example, exampleTranslation = exampleTrans, phonetic = phonetic
    )

    // ── Pack 1: Survival (A1) ──────
    fun getSurvival(): List<Word> {
        val p = "survival"; val l = "A1"
        return listOf(
            w("bonjour", "hello", p, l, "Bonjour, comment allez-vous?", "Hello, how are you?"),
            w("bonsoir", "good evening", p, l, "Bonsoir, une table pour deux?", "Good evening, a table for two?"),
            w("au revoir", "goodbye", p, l, "Au revoir et merci!", "Goodbye and thank you!"),
            w("merci", "thank you", p, l, "Merci beaucoup.", "Thank you very much."),
            w("s'il vous plaît", "please", p, l, "Un café, s'il vous plaît.", "A coffee, please."),
            w("pardon", "sorry", p, l, "Pardon, je n'ai pas compris.", "Sorry, I didn't understand."),
            w("excusez-moi", "excuse me", p, l, "Excusez-moi, où est la gare?", "Excuse me, where is the station?"),
            w("oui", "yes", p, l, "Oui, c'est correct.", "Yes, that is correct."),
            w("non", "no", p, l, "Non merci, ça va.", "No thank you, I'm fine."),
            w("je ne comprends pas", "I don't understand", p, l, "Je ne comprends pas, pouvez-vous répéter?", "I don't understand, can you repeat?"),
            w("je ne parle pas bien français", "I don't speak French well", p, l, "Je ne parle pas bien français, parlez lentement s'il vous plaît.", "I don't speak French well, please speak slowly."),
            w("parlez-vous anglais?", "do you speak English?", p, l, "Parlez-vous anglais?", "Do you speak English?"),
            w("où est...?", "where is...?", p, l, "Où est la pharmacie?", "Where is the pharmacy?"),
            w("combien ça coûte?", "how much does it cost?", p, l, "Combien ça coûte?", "How much does it cost?"),
            w("je voudrais...", "I would like...", p, l, "Je voudrais un café et un croissant.", "I would like a coffee and a croissant."),
            w("c'est combien?", "how much is it?", p, l, "C'est combien, s'il vous plaît?", "How much is it, please?"),
            w("je peux payer par carte?", "can I pay by card?", p, l, "Je peux payer par carte?", "Can I pay by card?"),
            w("l'addition, s'il vous plaît", "the bill, please", p, l, "L'addition, s'il vous plaît.", "The bill, please."),
            w("où sont les toilettes?", "where are the toilets?", p, l, "Excusez-moi, où sont les toilettes?", "Excuse me, where are the toilets?"),
            w("appelez le médecin", "call a doctor", p, l, "Appelez le médecin, s'il vous plaît.", "Please call a doctor."),
            w("appelez la police", "call the police", p, l, "Appelez la police!", "Call the police!"),
            w("j'ai besoin d'aide", "I need help", p, l, "J'ai besoin d'aide.", "I need help."),
            w("je m'appelle...", "my name is...", p, l, "Je m'appelle Thomas.", "My name is Thomas."),
            w("j'habite à...", "I live in...", p, l, "J'habite à Paris.", "I live in Paris."),
            w("je suis de...", "I am from...", p, l, "Je suis de Londres.", "I am from London."),
            w("quelle heure est-il?", "what time is it?", p, l, "Quelle heure est-il, s'il vous plaît?", "What time is it, please?"),
            w("à quelle heure?", "at what time?", p, l, "À quelle heure part le train?", "What time does the train leave?"),
            w("un billet pour..., s'il vous plaît", "one ticket to..., please", p, l, "Un billet pour Lyon, s'il vous plaît.", "One ticket to Lyon, please."),
            w("c'est loin?", "is it far?", p, l, "C'est loin d'ici?", "Is it far from here?"),
            w("à gauche / à droite / tout droit", "left / right / straight", p, l, "Tournez à gauche, puis tout droit.", "Turn left, then straight ahead."),
            w("j'ai faim / j'ai soif", "I'm hungry / thirsty", p, l, "J'ai faim, où peut-on manger?", "I'm hungry, where can we eat?"),
            w("je suis malade", "I am sick", p, l, "Je suis malade, j'ai besoin d'un médecin.", "I am ill, I need a doctor."),
            w("j'ai mal à...", "I have a pain in...", p, l, "J'ai mal à la tête. / J'ai mal au ventre.", "I have a headache. / I have a stomach ache."),
            w("une chambre pour une nuit", "a room for one night", p, l, "Une chambre pour une nuit, s'il vous plaît.", "A room for one night, please."),
            w("je suis perdu", "I am lost", p, l, "Je suis perdu, pouvez-vous m'aider?", "I am lost, can you help me?"),
            w("répétez s'il vous plaît", "please repeat", p, l, "Pouvez-vous répéter s'il vous plaît?", "Could you please repeat?"),
            w("plus lentement", "more slowly", p, l, "Parlez plus lentement, s'il vous plaît.", "Speak more slowly, please."),
            w("comment dit-on... en français?", "how do you say... in French?", p, l, "Comment dit-on \"window\" en français?", "How do you say \"window\" in French?"),
            w("qu'est-ce que c'est?", "what is this?", p, l, "Qu'est-ce que c'est?", "What is this?")
        )
    }
}
