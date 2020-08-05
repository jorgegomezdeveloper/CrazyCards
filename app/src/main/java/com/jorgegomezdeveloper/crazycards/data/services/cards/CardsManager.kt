package com.jorgegomezdeveloper.crazycards.data.services.cards

import com.jorgegomezdeveloper.crazycards.model.CardModel
import kotlin.random.Random

class CardsManager() {

    companion object {

        const val TYPE_START = 1
        const val TYPE_END = 4
        const val NUMBER_CARD_START = 2
        const val NUMBER_CARD_END = 14
        const val SYMBOL_J = "J"
        const val SYMBOL_Q = "Q"
        const val SYMBOL_K = "K"
        const val SYMBOL_A = "A"
    }

// Attributes
// =================================================================================================

    private var cardListOrdered: ArrayList<CardModel?>? = ArrayList()
    private var cardListRandom: ArrayList<CardModel?>? = ArrayList()
    private var cardListRandomUserLeft: ArrayList<CardModel?>? = ArrayList()
    private var cardListRandomUserRight: ArrayList<CardModel?>? = ArrayList()
    private var cardListDiscardsUserLeft: ArrayList<CardModel?>? = ArrayList()
    private var cardListDiscardsUserRight: ArrayList<CardModel?>? = ArrayList()

// Free Resources
// =================================================================================================

    fun freeResources() {

        this.cardListOrdered = ArrayList()
        this.cardListRandom = ArrayList()
        this.cardListRandomUserLeft = ArrayList()
        this.cardListRandomUserRight = ArrayList()
        this.cardListDiscardsUserLeft = ArrayList()
        this.cardListDiscardsUserRight = ArrayList()
    }

// Public methods - Builders
// =================================================================================================

    fun buildListOrdered(): ArrayList<CardModel?>? {

        for (i in TYPE_START..TYPE_END) {

            for (j in NUMBER_CARD_START..NUMBER_CARD_END) {

                when (j) {
                    11 -> { this.cardListOrdered?.add(CardModel(i.toString()+j.toString(),
                            i.toString(), j.toString(), SYMBOL_J)) }
                    12 -> { this.cardListOrdered?.add(CardModel(i.toString()+j.toString(),
                            i.toString(), j.toString(), SYMBOL_Q)) }
                    13 -> { this.cardListOrdered?.add(CardModel(i.toString()+j.toString(),
                            i.toString(), j.toString(), SYMBOL_K)) }
                    14 -> { this.cardListOrdered?.add(CardModel(i.toString()+j.toString(),
                            i.toString(), j.toString(), SYMBOL_A)) }
                    else -> { this.cardListOrdered?.add(CardModel(i.toString()+j.toString(),
                              i.toString(), j.toString(), j.toString()))}
                }
            }
        }

        return  this.cardListOrdered
    }

    fun buildListRandom(cardListOrdered: ArrayList<CardModel?>?): ArrayList<CardModel?>? {

        while (cardListOrdered!!.isNotEmpty()) {

            var positionRandom = 0

            if (cardListOrdered.size > 1) {
                positionRandom = Random.nextInt(0, cardListOrdered.size - 1)
            }

            this.cardListRandom?.add(cardListOrdered[positionRandom])
            cardListOrdered.removeAt(positionRandom)
        }

        return this.cardListRandom
    }

    fun buildListRandomLeft(cardListRandom: ArrayList<CardModel?>?): ArrayList<CardModel?>? {

        val sizeDivided = (cardListRandom?.size?.div(2))?.minus(1)

        for (i in 0..sizeDivided!!) {
            cardListRandomUserLeft?.add(cardListRandom[i])
        }

        while (cardListRandom.size > 26) {
            cardListRandom.removeAt(0)
        }

        return this.cardListRandomUserLeft
    }

    fun buildListRandomRight(cardListRandom: ArrayList<CardModel?>?): ArrayList<CardModel?>? {

        for (card in cardListRandom!!) {
            cardListRandomUserRight?.add(card)
        }

        cardListRandom.clear()

        return this.cardListRandomUserRight
    }

// Public methods - Adds
// =================================================================================================

    fun addCardsListDiscardsUserLeft(cardLeft: CardModel, cardRight: CardModel):
                                                                            ArrayList<CardModel?>? {
        this.cardListDiscardsUserLeft?.add(cardLeft)
        this.cardListDiscardsUserLeft?.add(cardRight)

        this.cardListRandomUserLeft?.remove(cardLeft)
        this.cardListRandomUserRight?.remove(cardRight)

        return this.cardListDiscardsUserLeft
    }

    fun addCardsListDiscardsUserRight(cardRight: CardModel, cardLeft: CardModel):
                                                                            ArrayList<CardModel?>? {
        this.cardListDiscardsUserRight?.add(cardRight)
        this.cardListDiscardsUserRight?.add(cardLeft)

        this.cardListRandomUserRight?.remove(cardRight)
        this.cardListRandomUserLeft?.remove(cardLeft)

        return this.cardListDiscardsUserRight
    }

// Public methods - Gets
// =================================================================================================

}