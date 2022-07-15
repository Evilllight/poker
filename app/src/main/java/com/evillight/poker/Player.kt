package com.evillight.poker

import java.util.*
import kotlin.collections.ArrayList

open class Player(FirstCard: Card?, SecondCard: Card?, ThirdCard: Card?, FourthCard: Card?, FifthCard: Card?){
    var handArrayList:ArrayList<Card?> = ArrayList()

     var handScore:Int

    fun showCards(){

        for (i in 0 until Constant.max_cards){
            println(handArrayList[i]!!.rankEnum.rankChar+"of"+handArrayList[i]!!.suitEnum.suitChar)

            if (i<Constant.max_cards-1) println(",")
        }
        println("")

    }
    init {
        handArrayList.add(FirstCard)
        handArrayList.add(SecondCard)
        handArrayList.add(ThirdCard)
        handArrayList.add(FourthCard)
        handArrayList.add(FifthCard)


        for (x in 0..4){
            for (y in 0..4){
                if (handArrayList[x]!!.rankEnum.ordinal>handArrayList[y]!!.rankEnum.ordinal){
                    Collections.swap(handArrayList,x,y)
                }
            }
        }
        handScore=-1
    }


}