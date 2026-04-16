package com.frenchquest.game.content

import com.frenchquest.data.models.Word

/**
 * Survival sentences only. Core content for the app.
 */
object VocabContent {

    fun getAllWords(): List<Word> = getSurvival()

    private fun w(french: String, english: String) = Word(
        french = french, english = english, packId = "survival", cefrLevel = "A1"
    )

    fun getSurvival(): List<Word> = listOf(
        w("Bonjour, comment allez-vous?", "Hello, how are you?"),
        w("Bonsoir, une table pour deux?", "Good evening, a table for two?"),
        w("Au revoir et merci!", "Goodbye and thank you!"),
        w("Merci beaucoup.", "Thank you very much."),
        w("Un café, s'il vous plaît.", "A coffee, please."),
        w("Pardon, je n'ai pas compris.", "Sorry, I didn't understand."),
        w("Excusez-moi, où est la gare?", "Excuse me, where is the station?"),
        w("Oui, c'est correct.", "Yes, that is correct."),
        w("Non merci, ça va.", "No thank you, I'm fine."),
        w("Je ne comprends pas, pouvez-vous répéter?", "I don't understand, can you repeat?"),
        w("Je ne parle pas bien français, parlez lentement s'il vous plaît.", "I don't speak French well, please speak slowly."),
        w("Parlez-vous anglais?", "Do you speak English?"),
        w("Où est la pharmacie?", "Where is the pharmacy?"),
        w("Combien ça coûte?", "How much does it cost?"),
        w("Je voudrais un café et un croissant.", "I would like a coffee and a croissant."),
        w("C'est combien, s'il vous plaît?", "How much is it, please?"),
        w("Je peux payer par carte?", "Can I pay by card?"),
        w("L'addition, s'il vous plaît.", "The bill, please."),
        w("Excusez-moi, où sont les toilettes?", "Excuse me, where are the toilets?"),
        w("Appelez le médecin, s'il vous plaît.", "Please call a doctor."),
        w("Appelez la police!", "Call the police!"),
        w("J'ai besoin d'aide.", "I need help."),
        w("Je m'appelle Thomas.", "My name is Thomas."),
        w("J'habite à Paris.", "I live in Paris."),
        w("Je suis de Londres.", "I am from London."),
        w("Quelle heure est-il, s'il vous plaît?", "What time is it, please?"),
        w("À quelle heure part le train?", "What time does the train leave?"),
        w("Un billet pour Lyon, s'il vous plaît.", "One ticket to Lyon, please."),
        w("C'est loin d'ici?", "Is it far from here?"),
        w("Tournez à gauche, puis tout droit.", "Turn left, then straight ahead."),
        w("J'ai faim, où peut-on manger?", "I'm hungry, where can we eat?"),
        w("Je suis malade, j'ai besoin d'un médecin.", "I am ill, I need a doctor."),
        w("J'ai mal à la tête.", "I have a headache."),
        w("J'ai mal au ventre.", "I have a stomach ache."),
        w("Une chambre pour une nuit, s'il vous plaît.", "A room for one night, please."),
        w("Je suis perdu, pouvez-vous m'aider?", "I am lost, can you help me?"),
        w("Pouvez-vous répéter s'il vous plaît?", "Could you please repeat?"),
        w("Parlez plus lentement, s'il vous plaît.", "Speak more slowly, please."),
        w("Comment dit-on \"window\" en français?", "How do you say \"window\" in French?"),
        w("Qu'est-ce que c'est?", "What is this?")
    )
}
