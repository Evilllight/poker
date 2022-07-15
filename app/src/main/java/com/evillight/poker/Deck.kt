package com.evillight.poker


import kotlin.collections.ArrayList

 class Deck {

    private val deck :ArrayList<Card?> =ArrayList()
    private var topCardIndex:Int = 51

    fun drawCard(): Card? {
        val drawCards=deck[topCardIndex]
        deck.removeAt(topCardIndex)
        topCardIndex -= 1
        return drawCards
    }

    fun shuffle(){
        deck.shuffle()
    }
    init {
        for (s in Card.Suit.values()){
            for (r in Card.Rank.values()){
                deck.add(Card(s,r))
            }
        }
        deck.shuffle()
    }
}