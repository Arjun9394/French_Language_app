package com.frenchquest.game.content

/**
 * Survival dialogue scenarios.
 */
object DialogueContent {

    data class Dialogue(
        val emoji: String,
        val title: String,
        val exchanges: List<Exchange>
    )

    data class Exchange(
        val npcLine: String,
        val npcLineTranslation: String?,
        val choices: List<Choice>
    )

    data class Choice(
        val french: String,
        val english: String,
        val isCorrect: Boolean
    )

    fun getAllDialogues(): List<Dialogue> = listOf(
        cafeDialogue(), directionsDialogue(), hotelDialogue(),
        shopDialogue(), schoolDialogue(), doctorDialogue(), transportDialogue()
    )

    private fun cafeDialogue() = Dialogue(
        emoji = "☕", title = "Au café",
        exchanges = listOf(
            Exchange("Bonjour ! Qu'est-ce que vous désirez ?", "Hello! What would you like?", listOf(
                Choice("Un café, s'il vous plaît.", "A coffee, please.", true),
                Choice("Je suis un café.", "I am a coffee.", false),
                Choice("Bonjour le café !", "Hello the coffee!", false),
            )),
            Exchange("Très bien. Avec du sucre ?", "Very well. With sugar?", listOf(
                Choice("Oui, deux sucres, s'il vous plaît.", "Yes, two sugars, please.", true),
                Choice("Oui, je veux du gâteau.", "Yes, I want cake.", false),
                Choice("Non, avec du pain.", "No, with bread.", false),
            )),
            Exchange("Et voilà ! Ça fait deux euros cinquante.", "Here you go! That's €2.50.", listOf(
                Choice("Voici, merci beaucoup !", "Here you are, thank you!", true),
                Choice("C'est trop cher !", "That's too expensive!", false),
                Choice("Je ne comprends pas.", "I don't understand.", false),
            ))
        )
    )

    private fun directionsDialogue() = Dialogue(
        emoji = "🗺️", title = "Les directions",
        exchanges = listOf(
            Exchange("Excusez-moi, vous cherchez quelque chose ?", "Excuse me, are you looking for something?", listOf(
                Choice("Oui, où est la gare, s'il vous plaît ?", "Yes, where is the station, please?", true),
                Choice("Oui, je suis perdu.", "Yes, I am lost.", false),
                Choice("Non, je suis français.", "No, I am French.", false),
            )),
            Exchange("La gare est à gauche, puis tout droit.", "The station is left, then straight ahead.", listOf(
                Choice("Merci beaucoup ! À gauche puis tout droit.", "Thank you! Left then straight ahead.", true),
                Choice("Merci ! À droite puis tout droit.", "Thanks! Right then straight.", false),
                Choice("D'accord, je tourne en rond.", "OK, I go in circles.", false),
            )),
            Exchange("De rien ! Bonne journée !", "You're welcome! Have a good day!", listOf(
                Choice("Merci, bonne journée à vous aussi !", "Thanks, have a good day too!", true),
                Choice("Oui oui.", "Yeah yeah.", false),
                Choice("Au revoir le monsieur.", "Goodbye the mister.", false),
            ))
        )
    )

    private fun hotelDialogue() = Dialogue(
        emoji = "🏨", title = "À l'hôtel",
        exchanges = listOf(
            Exchange("Bonsoir ! Vous avez une réservation ?", "Good evening! Do you have a reservation?", listOf(
                Choice("Oui, au nom de Martin.", "Yes, under the name Martin.", true),
                Choice("Oui, je veux dormir.", "Yes, I want to sleep.", false),
                Choice("Non, je cherche un restaurant.", "No, I'm looking for a restaurant.", false),
            )),
            Exchange("Pour combien de nuits ?", "For how many nights?", listOf(
                Choice("Trois nuits, s'il vous plaît.", "Three nights, please.", true),
                Choice("Beaucoup de nuits.", "A lot of nights.", false),
                Choice("Je ne sais pas encore.", "I don't know yet.", false),
            )),
            Exchange("Votre chambre est au deuxième étage. Voici la clé.", "Your room is on the second floor. Here's the key.", listOf(
                Choice("Merci ! Le petit-déjeuner est à quelle heure ?", "Thanks! What time is breakfast?", true),
                Choice("Merci ! Où est la plage ?", "Thanks! Where is the beach.", false),
                Choice("C'est trop haut.", "It's too high.", false),
            ))
        )
    )

    private fun shopDialogue() = Dialogue(
        emoji = "🛍️", title = "Au magasin",
        exchanges = listOf(
            Exchange("Bonjour ! Je peux vous aider ?", "Hello! Can I help you?", listOf(
                Choice("Oui, je cherche une robe.", "Yes, I'm looking for a dress.", true),
                Choice("Non, je regarde.", "No, I'm just looking.", false),
                Choice("Oui, je veux manger.", "Yes, I want to eat.", false),
            )),
            Exchange("Quelle taille ?", "What size?", listOf(
                Choice("Taille moyenne, s'il vous plaît.", "Medium size, please.", true),
                Choice("Grande comme la tour Eiffel.", "Big like the Eiffel Tower.", false),
                Choice("Je ne sais pas ce que c'est.", "I don't know what that is.", false),
            )),
            Exchange("Celle-ci coûte quarante euros.", "This one costs forty euros.", listOf(
                Choice("C'est parfait, je la prends !", "That's perfect, I'll take it!", true),
                Choice("C'est gratuit ?", "Is it free?", false),
                Choice("Je préfère le fromage.", "I prefer cheese.", false),
            ))
        )
    )

    private fun schoolDialogue() = Dialogue(
        emoji = "📚", title = "À l'école",
        exchanges = listOf(
            Exchange("Bienvenue dans la classe ! Comment vous appelez-vous ?", "Welcome to the class! What's your name?", listOf(
                Choice("Je m'appelle Alex. Enchanté(e) !", "My name is Alex. Nice to meet you!", true),
                Choice("J'appelle Alex.", "I call Alex.", false),
                Choice("Mon nom Alex.", "My name Alex.", false),
            )),
            Exchange("Ouvrez vos livres à la page dix.", "Open your books to page ten.", listOf(
                Choice("D'accord, professeur.", "OK, teacher.", true),
                Choice("Je n'ai pas de livre.", "I don't have a book.", false),
                Choice("Qu'est-ce qu'un livre ?", "What is a book?", false),
            )),
        )
    )

    private fun doctorDialogue() = Dialogue(
        emoji = "🏥", title = "Chez le médecin",
        exchanges = listOf(
            Exchange("Bonjour, qu'est-ce qui ne va pas ?", "Hello, what's wrong?", listOf(
                Choice("J'ai mal à la tête et j'ai de la fièvre.", "I have a headache and a fever.", true),
                Choice("Je suis très beau.", "I am very handsome.", false),
                Choice("J'ai mal au fromage.", "My cheese hurts.", false),
            )),
            Exchange("Depuis combien de temps ?", "For how long?", listOf(
                Choice("Depuis deux jours.", "For two days.", true),
                Choice("Depuis toujours.", "Since forever.", false),
                Choice("Depuis le fromage.", "Since the cheese.", false),
            )),
            Exchange("Prenez ce médicament trois fois par jour.", "Take this medicine three times a day.", listOf(
                Choice("D'accord, merci docteur. Au revoir !", "OK, thank you doctor. Goodbye!", true),
                Choice("Non merci, je préfère le chocolat.", "No thanks, I prefer chocolate.", false),
                Choice("Trois fois ? C'est beaucoup !", "Three times? That's a lot!", false),
            ))
        )
    )

    private fun transportDialogue() = Dialogue(
        emoji = "🚇", title = "Dans le métro",
        exchanges = listOf(
            Exchange("Bonjour, vous désirez ?", "Hello, what would you like?", listOf(
                Choice("Un ticket de métro, s'il vous plaît.", "A metro ticket, please.", true),
                Choice("Je veux aller sous la terre.", "I want to go underground.", false),
                Choice("Un avion, s'il vous plaît.", "A plane, please.", false),
            )),
            Exchange("Un aller simple ou un aller-retour ?", "One way or round trip?", listOf(
                Choice("Un aller-retour, s'il vous plaît.", "Round trip, please.", true),
                Choice("Les deux.", "Both.", false),
                Choice("Je ne sais pas où je vais.", "I don't know where I'm going.", false),
            )),
            Exchange("C'est un euro quatre-vingt-dix.", "That's €1.90.", listOf(
                Choice("Voici deux euros. Merci !", "Here's two euros. Thanks!", true),
                Choice("C'est combien en dollars ?", "How much in dollars?", false),
                Choice("Je peux payer demain ?", "Can I pay tomorrow?", false),
            ))
        )
    )
}
