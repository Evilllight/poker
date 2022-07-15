package com.evillight.poker

object PokerStart{
    fun main(args:Array<String>){
        val gameDeck=Deck()
        val hand=Hand(gameDeck)
        println("The Decks is being shuffled")
        println("The Cards are being dealt to ${Constant.max_players} Players")
        hand.calculateWinner(gameDeck)
    }

}