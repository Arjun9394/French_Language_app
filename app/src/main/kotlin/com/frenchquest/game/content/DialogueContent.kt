package com.frenchquest.game.content

/**
 * All A1/A2 dialogue scenarios for the Dialogue mini-game.
 * 7 real-life scenarios, each with 3-4 exchanges.
 */
object DialogueContent {

    data class Dialogue(
        val emoji: String,
        val title: String,
        val context: String,
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
        val isCorrect: Boolean,
        val consequence: String?
    )

    fun getAllDialogues(): List<Dialogue> = listOf(
        cafeDialogue(), directionsDialogue(), hotelDialogue(),
        shopDialogue(), schoolDialogue(), doctorDialogue(), transportDialogue()
    )

    private fun cafeDialogue() = Dialogue(
        emoji = "☕", title = "Au café", context = "You walk into a Parisian café.",
        exchanges = listOf(
            Exchange("Bonjour ! Qu'est-ce que vous désirez ?", "Hello! What would you like?", listOf(
                Choice("Un café, s'il vous plaît.", "A coffee, please.", true, "The waiter smiles and nods."),
                Choice("Je suis un café.", "I am a coffee.", false, "The waiter looks confused…"),
                Choice("Bonjour le café !", "Hello the coffee!", false, "That's not quite right…"),
            )),
            Exchange("Très bien. Avec du sucre ?", "Very well. With sugar?", listOf(
                Choice("Oui, deux sucres, s'il vous plaît.", "Yes, two sugars, please.", true, "Perfect French!"),
                Choice("Oui, je veux du gâteau.", "Yes, I want cake.", false, "He asked about sugar, not cake!"),
                Choice("Non, avec du pain.", "No, with bread.", false, "Coffee with bread?"),
            )),
            Exchange("Et voilà ! Ça fait deux euros cinquante.", "Here you go! That's €2.50.", listOf(
                Choice("Voici, merci beaucoup !", "Here you are, thank you!", true, "You enjoy your café. Parfait !"),
                Choice("C'est trop cher !", "That's too expensive!", false, "A bit rude for a café…"),
                Choice("Je ne comprends pas.", "I don't understand.", false, "He said the price clearly."),
            ))
        )
    )

    private fun directionsDialogue() = Dialogue(
        emoji = "🗺️", title = "Les directions", context = "You're lost near the Eiffel Tower.",
        exchanges = listOf(
            Exchange("Excusez-moi, vous cherchez quelque chose ?", "Excuse me, are you looking for something?", listOf(
                Choice("Oui, où est la gare, s'il vous plaît ?", "Yes, where is the station, please?", true, "Polite and clear!"),
                Choice("Oui, je suis perdu.", "Yes, I am lost.", false, "True, but you need to ask for directions!"),
                Choice("Non, je suis français.", "No, I am French.", false, "But you look like you need help…"),
            )),
            Exchange("La gare est à gauche, puis tout droit.", "The station is left, then straight ahead.", listOf(
                Choice("Merci beaucoup ! À gauche puis tout droit.", "Thank you! Left then straight ahead.", true, "You repeated the directions perfectly!"),
                Choice("Merci ! À droite puis tout droit.", "Thanks! Right then straight.", false, "No — left, not right!"),
                Choice("D'accord, je tourne en rond.", "OK, I go in circles.", false, "That won't help you get there!"),
            )),
            Exchange("De rien ! Bonne journée !", "You're welcome! Have a good day!", listOf(
                Choice("Merci, bonne journée à vous aussi !", "Thanks, have a good day too!", true, "Polite response. Well done!"),
                Choice("Oui oui.", "Yeah yeah.", false, "A bit dismissive…"),
                Choice("Au revoir le monsieur.", "Goodbye the mister.", false, "Almost — just 'Au revoir, monsieur !'"),
            ))
        )
    )

    private fun hotelDialogue() = Dialogue(
        emoji = "🏨", title = "À l'hôtel", context = "You arrive at your hotel to check in.",
        exchanges = listOf(
            Exchange("Bonsoir ! Vous avez une réservation ?", "Good evening! Do you have a reservation?", listOf(
                Choice("Oui, au nom de Martin.", "Yes, under the name Martin.", true, "Perfect check-in phrase!"),
                Choice("Oui, je veux dormir.", "Yes, I want to sleep.", false, "True, but not the right answer…"),
                Choice("Non, je cherche un restaurant.", "No, I'm looking for a restaurant.", false, "This is a hotel, not a restaurant."),
            )),
            Exchange("Pour combien de nuits ?", "For how many nights?", listOf(
                Choice("Trois nuits, s'il vous plaît.", "Three nights, please.", true, "Clear and polite!"),
                Choice("Beaucoup de nuits.", "A lot of nights.", false, "They need a specific number."),
                Choice("Je ne sais pas encore.", "I don't know yet.", false, "Hotels need to know the duration!"),
            )),
            Exchange("Votre chambre est au deuxième étage. Voici la clé.", "Your room is on the second floor. Here's the key.", listOf(
                Choice("Merci ! Le petit-déjeuner est à quelle heure ?", "Thanks! What time is breakfast?", true, "Great follow-up question!"),
                Choice("Merci ! Où est la plage ?", "Thanks! Where is the beach?", false, "There might not be a beach nearby…"),
                Choice("C'est trop haut.", "It's too high.", false, "The second floor isn't that high!"),
            ))
        )
    )

    private fun shopDialogue() = Dialogue(
        emoji = "🛍️", title = "Au magasin", context = "You're shopping for clothes.",
        exchanges = listOf(
            Exchange("Bonjour ! Je peux vous aider ?", "Hello! Can I help you?", listOf(
                Choice("Oui, je cherche une robe.", "Yes, I'm looking for a dress.", true, "Clear request!"),
                Choice("Non, je regarde.", "No, I'm just looking.", false, "You actually need help!"),
                Choice("Oui, je veux manger.", "Yes, I want to eat.", false, "This is a clothing store…"),
            )),
            Exchange("Quelle taille ?", "What size?", listOf(
                Choice("Taille moyenne, s'il vous plaît.", "Medium size, please.", true, "Perfect!"),
                Choice("Grande comme la tour Eiffel.", "Big like the Eiffel Tower.", false, "That's a bit too big!"),
                Choice("Je ne sais pas ce que c'est.", "I don't know what that is.", false, "'Taille' means size."),
            )),
            Exchange("Celle-ci coûte quarante euros.", "This one costs forty euros.", listOf(
                Choice("C'est parfait, je la prends !", "That's perfect, I'll take it!", true, "Great shopping phrase!"),
                Choice("C'est gratuit ?", "Is it free?", false, "She just told you the price!"),
                Choice("Je préfère le fromage.", "I prefer cheese.", false, "We're buying clothes here…"),
            ))
        )
    )

    private fun schoolDialogue() = Dialogue(
        emoji = "📚", title = "À l'école", context = "First day in a French language class.",
        exchanges = listOf(
            Exchange("Bienvenue dans la classe ! Comment vous appelez-vous ?", "Welcome to the class! What's your name?", listOf(
                Choice("Je m'appelle Alex. Enchanté(e) !", "My name is Alex. Nice to meet you!", true, "Perfect introduction!"),
                Choice("J'appelle Alex.", "I call Alex.", false, "Missing 'me' — 'Je m'appelle'"),
                Choice("Mon nom Alex.", "My name Alex.", false, "Missing the verb completely."),
            )),
            Exchange("Ouvrez vos livres à la page dix.", "Open your books to page ten.", listOf(
                Choice("D'accord, professeur.", "OK, teacher.", true, "Polite and natural!"),
                Choice("Je n'ai pas de livre.", "I don't have a book.", false, "You should have brought it!"),
                Choice("Qu'est-ce qu'un livre ?", "What is a book?", false, "You should know what 'livre' means!"),
            )),
        )
    )

    private fun doctorDialogue() = Dialogue(
        emoji = "🏥", title = "Chez le médecin", context = "You're not feeling well and visit a doctor.",
        exchanges = listOf(
            Exchange("Bonjour, qu'est-ce qui ne va pas ?", "Hello, what's wrong?", listOf(
                Choice("J'ai mal à la tête et j'ai de la fièvre.", "I have a headache and a fever.", true, "Clear symptoms description!"),
                Choice("Je suis très beau.", "I am very handsome.", false, "That's nice, but why are you here?"),
                Choice("J'ai mal au fromage.", "My cheese hurts.", false, "'Fromage' is cheese, not a body part!"),
            )),
            Exchange("Depuis combien de temps ?", "For how long?", listOf(
                Choice("Depuis deux jours.", "For two days.", true, "Good use of 'depuis'!"),
                Choice("Depuis toujours.", "Since forever.", false, "That seems unlikely…"),
                Choice("Depuis le fromage.", "Since the cheese.", false, "Cheese again?"),
            )),
            Exchange("Prenez ce médicament trois fois par jour.", "Take this medicine three times a day.", listOf(
                Choice("D'accord, merci docteur. Au revoir !", "OK, thank you doctor. Goodbye!", true, "Perfect patient response!"),
                Choice("Non merci, je préfère le chocolat.", "No thanks, I prefer chocolate.", false, "Medicine isn't optional!"),
                Choice("Trois fois ? C'est beaucoup !", "Three times? That's a lot!", false, "Trust the doctor!"),
            ))
        )
    )

    private fun transportDialogue() = Dialogue(
        emoji = "🚇", title = "Dans le métro", context = "You need to buy a metro ticket.",
        exchanges = listOf(
            Exchange("Bonjour, vous désirez ?", "Hello, what would you like?", listOf(
                Choice("Un ticket de métro, s'il vous plaît.", "A metro ticket, please.", true, "Simple and effective!"),
                Choice("Je veux aller sous la terre.", "I want to go underground.", false, "Technically true but not helpful!"),
                Choice("Un avion, s'il vous plaît.", "A plane, please.", false, "This is the metro, not the airport!"),
            )),
            Exchange("Un aller simple ou un aller-retour ?", "One way or round trip?", listOf(
                Choice("Un aller-retour, s'il vous plaît.", "Round trip, please.", true, "Common travel phrase!"),
                Choice("Les deux.", "Both.", false, "An 'aller-retour' IS both!"),
                Choice("Je ne sais pas où je vais.", "I don't know where I'm going.", false, "You should figure that out first!"),
            )),
            Exchange("C'est un euro quatre-vingt-dix.", "That's €1.90.", listOf(
                Choice("Voici deux euros. Merci !", "Here's two euros. Thanks!", true, "Smooth transaction!"),
                Choice("C'est combien en dollars ?", "How much in dollars?", false, "You're in France — euros!"),
                Choice("Je peux payer demain ?", "Can I pay tomorrow?", false, "That's not how tickets work!"),
            ))
        )
    )
}
