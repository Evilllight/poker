package com.evillight.poker

class Card (val suit:Suit,val rank:Rank){

    enum class Suit(val suitChar:Char){
        CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S')
    }
    enum class Rank(var rankChar:Char){
        TWO('2'),
        THREE('3'),
        FOUR('4'),
        FIVE('5'),
        SIX('6'),
        SEVEN('7'),
        EIGHT('8'),
        NINE('9'),
        TEN('T'),
        JACK('J'),
        QUEEN('Q'),
        KING('K'),
        ACE('A')
    }
    val suitChar:Char get() =suit.suitChar

    fun SuitName(ch:Char): String {
        var suite=""
        when (ch) {
            'C' -> suite = "CLUBS"
            'D' -> suite = "DIAMONDS"
            'H' -> suite = "HEARTS"
            'S' -> suite = "SPADE"
        }
        return suite
    }

    fun RankName(ch:Char): String {
        var rank = ""
        rank =
            when (ch) {
                'T' -> "10"
                'A' -> "Ace"
                'J' -> "Jack"
                'Q' -> "Queen"
                'K' -> "King"
                else -> ch.toString()
            }
        return rank
    }

    var rankEnum:Rank
        get() = rank
        set(rank){this.rank==rank}
    var suitEnum:Suit
        get() = suit
        set(suit) {this.suit==suit}

}