package com.evillight.poker

import java.util.*

class Hand {
    private var player1:Player ?= null
    private var player2:Player ?= null

    constructor(gameDeck: Deck){
    player1= initPlayer(gameDeck)
    player2= initPlayer(gameDeck)
    }

    constructor()

    private fun initPlayer(gameDeck: Deck):Player{
        val cards= arrayOfNulls<Card>(Constant.max_cards)

        for (i in 0 until Constant.max_cards){
            gameDeck.shuffle()
            cards[i]=gameDeck.drawCard()
        }

        return Player(cards[0],cards[1],cards[2],cards[3],cards[4])
    }

    private fun evaluateHand(player:Player?):Int{
        for (x in 0 until Constant.max_players){
            for (y in  0 until Constant.max_players){
                if (player!!.handArrayList[x]!!.rankEnum.ordinal>player.handArrayList[y]!!.rankEnum.ordinal){
                    Collections.swap(player.handArrayList,x,y)
                }
            }
        }
        val firstCardRankOrdinal = player!!.handArrayList[0]!!.rankEnum.ordinal
        val secondCardRankOrdinal = player.handArrayList[1]!!.rankEnum.ordinal
        val thirdCardRankOrdinal = player.handArrayList[2]!!.rankEnum.ordinal
        val fourthCardRankOrdinal = player.handArrayList[3]!!.rankEnum.ordinal
        val fifthCardRankOrdinal = player.handArrayList[4]!!.rankEnum.ordinal
        val firstCardSuitOrdinal = player.handArrayList[0]!!.suitEnum.ordinal
        val secondCardSuitOrdinal = player.handArrayList[1]!!.suitEnum.ordinal
        val thirdCardSuitOrdinal = player.handArrayList[2]!!.suitEnum.ordinal
        val fourthCardSuitOrdinal = player.handArrayList[3]!!.suitEnum.ordinal
        val fifthCardSuitOrdinal = player.handArrayList[4]!!.suitEnum.ordinal

        return if (firstCardRankOrdinal == 12 /*ACE*/ &&
            secondCardRankOrdinal == 11 /*KING*/ &&
            thirdCardRankOrdinal == 10 /*QUEEN*/ &&
            fourthCardRankOrdinal == 9 /*JACK*/ &&
            fifthCardRankOrdinal == 8 /*10*/ &&
            firstCardSuitOrdinal == secondCardSuitOrdinal &&
            secondCardSuitOrdinal == thirdCardSuitOrdinal &&
            thirdCardSuitOrdinal == fourthCardSuitOrdinal &&
            fourthCardSuitOrdinal == fifthCardSuitOrdinal
        ) {
            println("royal flush \n")
            player.handScore = 9
            9
        } else if (firstCardRankOrdinal == secondCardRankOrdinal + 1 &&
            firstCardRankOrdinal == thirdCardRankOrdinal + 2 &&
            firstCardRankOrdinal == fourthCardRankOrdinal + 3 && firstCardRankOrdinal == fifthCardRankOrdinal + 4 && firstCardSuitOrdinal == secondCardSuitOrdinal && secondCardSuitOrdinal == thirdCardSuitOrdinal && thirdCardSuitOrdinal == fourthCardSuitOrdinal && fourthCardSuitOrdinal == fifthCardSuitOrdinal
        ) {
            println("straight flush \n")
            player.handScore = 8
            8
        } else if (firstCardRankOrdinal == 12 && firstCardRankOrdinal == secondCardRankOrdinal + 9 && firstCardRankOrdinal == thirdCardRankOrdinal + 10 && firstCardRankOrdinal == fourthCardRankOrdinal + 11 && firstCardRankOrdinal == fifthCardRankOrdinal + 12 && firstCardSuitOrdinal == secondCardSuitOrdinal && secondCardSuitOrdinal == thirdCardSuitOrdinal && thirdCardSuitOrdinal == fourthCardSuitOrdinal && fourthCardSuitOrdinal == fifthCardSuitOrdinal) {
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            println("straight flush with Ace as low card - returns 8")
            player.handScore = 8
            8
        } else if (firstCardRankOrdinal == secondCardRankOrdinal &&
            firstCardRankOrdinal == thirdCardRankOrdinal &&
            firstCardRankOrdinal == fourthCardRankOrdinal
        ) {
            println("four of a kind on 1st 4 cards - returns 7\n")
            player.handScore = 7
            7
        } else if (secondCardRankOrdinal == thirdCardRankOrdinal &&
            secondCardRankOrdinal == fourthCardRankOrdinal &&
            secondCardRankOrdinal == fifthCardRankOrdinal
        ) {
            println("four of a kind on 2nd 4 cards - returns 7\n")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            player.handScore = 7
            7
        } else if (firstCardRankOrdinal == secondCardRankOrdinal &&
            firstCardRankOrdinal == thirdCardRankOrdinal &&
            fourthCardRankOrdinal == fifthCardRankOrdinal &&
            firstCardRankOrdinal != fifthCardRankOrdinal
        ) {
            println("full house 3 pair, 2 pair\n")
            player.handScore = 6
            6
        } else if (firstCardRankOrdinal == secondCardRankOrdinal &&
            thirdCardRankOrdinal == fourthCardRankOrdinal &&
            thirdCardRankOrdinal == fifthCardRankOrdinal &&
            firstCardRankOrdinal != fifthCardRankOrdinal
        ) {
            println("full house 2 pair, then 3 pair\n")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            Collections.swap(player.handArrayList, 0, 3)
            player.handScore = 6
            6
        } else if (firstCardSuitOrdinal == secondCardSuitOrdinal && thirdCardSuitOrdinal == fourthCardSuitOrdinal && fourthCardSuitOrdinal == fifthCardSuitOrdinal && firstCardSuitOrdinal == thirdCardSuitOrdinal && firstCardSuitOrdinal == fourthCardSuitOrdinal && thirdCardSuitOrdinal == fifthCardSuitOrdinal) {
            println("flush\n")
            player.handScore = 5
            5
        } else if (firstCardRankOrdinal == secondCardRankOrdinal + 1 &&
            firstCardRankOrdinal == thirdCardRankOrdinal + 2 &&
            firstCardRankOrdinal == fourthCardRankOrdinal + 3 && firstCardRankOrdinal == fifthCardRankOrdinal + 4
        ) {
            println("straight \n")
            player.handScore = 4
            4
        } else if (firstCardRankOrdinal == 12 && firstCardRankOrdinal == secondCardRankOrdinal + 9 && firstCardRankOrdinal == thirdCardRankOrdinal + 10 && firstCardRankOrdinal == fourthCardRankOrdinal + 11 && firstCardRankOrdinal == fifthCardRankOrdinal + 12) {
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            println("straight with an ace")
            player.handScore = 4
            4
        } else if (firstCardRankOrdinal == secondCardRankOrdinal &&
            secondCardRankOrdinal == thirdCardRankOrdinal
        ) {
            println("Three of a kind on 1st 3 cards")
            player.handScore = 3
            3
        } else if (secondCardRankOrdinal == thirdCardRankOrdinal &&
            thirdCardRankOrdinal == fourthCardRankOrdinal
        ) {
            println("Three of a kind on 2st 3 cards")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            player.handScore = 3
            3
        } else if (thirdCardRankOrdinal == fourthCardRankOrdinal &&
            fourthCardRankOrdinal == fifthCardRankOrdinal
        ) {
            println("Three of a kind on 3st 3 cards")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            player1!!.showCards()
            player.handScore = 3
            3
        } else if (firstCardRankOrdinal == secondCardRankOrdinal && thirdCardRankOrdinal == fourthCardRankOrdinal && firstCardRankOrdinal != thirdCardRankOrdinal) {
            println("Two pair on 1st 2 cards and 2nd 2 cards")
            //would already be sorted correctly for Extra credit
            player.handScore = 2
            2
        } else if (secondCardRankOrdinal == thirdCardRankOrdinal && fourthCardRankOrdinal == fifthCardRankOrdinal && firstCardRankOrdinal >= secondCardRankOrdinal) {
            println("Two pair on 2nd pair of cards and last pair of cards")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            player.handScore = 2
            2
        } else if (firstCardRankOrdinal == secondCardRankOrdinal && fourthCardRankOrdinal == fifthCardRankOrdinal && thirdCardRankOrdinal >= fourthCardRankOrdinal) {
            println("Two pair")
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            player.handScore = 2
            2
        } else if (firstCardRankOrdinal == secondCardRankOrdinal && firstCardRankOrdinal != thirdCardRankOrdinal && firstCardRankOrdinal != fourthCardRankOrdinal && firstCardRankOrdinal != fifthCardRankOrdinal) {
            println("One Pair or Two of a Kind on 1st 2 cards")

            //would already be sorted correctly for Extra credit
            player.handScore = 1
            1
        } else if (secondCardRankOrdinal == thirdCardRankOrdinal && firstCardRankOrdinal != secondCardRankOrdinal && fourthCardRankOrdinal != secondCardRankOrdinal && fifthCardRankOrdinal != secondCardRankOrdinal) {
            println("One Pair or Two of a Kind on 2nd 2 cards")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            player.handScore = 1
            1
        } else if (thirdCardRankOrdinal == fourthCardRankOrdinal && thirdCardRankOrdinal != firstCardRankOrdinal && thirdCardRankOrdinal != secondCardRankOrdinal && thirdCardRankOrdinal != fifthCardRankOrdinal) {
            println("One Pair or Two of a Kind on 3rd 2 cards")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)

            //player.showCards();
            player.handScore = 1
            1
        } else if (fourthCardRankOrdinal == fifthCardRankOrdinal && fourthCardRankOrdinal != firstCardRankOrdinal && fourthCardRankOrdinal != secondCardRankOrdinal && fourthCardRankOrdinal != thirdCardRankOrdinal) {
            println("One Pair or Two of a Kind on last 2 cards")

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 3, 4)
            Collections.swap(player.handArrayList, 1, 2)
            Collections.swap(player.handArrayList, 2, 3)
            Collections.swap(player.handArrayList, 0, 1)
            Collections.swap(player.handArrayList, 1, 2)
            player.handScore = 1
            1
        } else {
            println("high card")
            player.handScore = 0
            0
        }

    }

    fun calculateWinner(gameDeck: Deck){
        println("Player1 cards:")
        player1?.showCards()
        println("Player2 cards:")
        player2?.showCards()

        val winner:Player?
        evaluateCards(gameDeck)
        winner=whoWillWin(player1,player2)
        println("-----------------------")
        println("Winner is:")
        if (winner==player1){
            println(player1)
        }else{println(player2)}
        winner.showCards()
        println("-----------")
    }

    private fun whoWillWin(a:Player?, b:Player?):Player{
        if (a!!.handScore>b!!.handScore){
            return a
        }else if (a.handScore==b.handScore){
            var x=0
            while (x<Constant.max_cards){
                if (a.handArrayList[x]!!.rankEnum.ordinal > b.handArrayList[x]!!.rankEnum.ordinal){
                    return a
                }else if (a.handArrayList[x]!!.rankEnum.ordinal<b.handArrayList[x]!!.rankEnum.ordinal){
                    return b
                }else{
                    x++
                }
            }
        }
        return b
    }
    private fun evaluateCards(gameDeck: Deck){
        println("Player1 has :")
        player1!!.handScore=evaluateHand(player1)
        println("Player 2 has :")
        player2!!.handScore=evaluateHand(player2)

    }

}