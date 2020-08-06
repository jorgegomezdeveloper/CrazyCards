package com.jorgegomezdeveloper.crazycards.data.services.play

import com.jorgegomezdeveloper.crazycards.model.CardModel

class PlayManager {

    companion object {

        const val WIN_USER_LEFT = "left"
        const val WIN_USER_RIGHT = "right"
        const val MAX_ROUNDS = 26
    }

// Attributes
// =================================================================================================

    private var isPutCardLeft: Boolean = false
    private var isPutCardRight: Boolean = false
    private var counterRounds: Int = 0
    private var counterRWonUserLeft: Int = 0
    private var counterRWonUserRight: Int = 0
    private var counterDiscardsUserLeft: Int = 0
    private var counterDiscardsUserRight: Int = 0

// Free Resources
// =================================================================================================

    fun freeResources() {

        this.isPutCardLeft = false
        this.isPutCardRight = false
        this.counterRounds = 0
        this.counterRWonUserLeft = 0
        this.counterRWonUserRight = 0
        this.counterDiscardsUserLeft = 0
        this.counterDiscardsUserRight = 0
    }

// Public methods - Cards
// =================================================================================================

    fun setPutCardLeft(isPutCardLeft: Boolean) {
        this.isPutCardLeft = isPutCardLeft
    }

    fun setPutCardRight(isPutCardRight: Boolean) {
        this.isPutCardRight = isPutCardRight
    }

    fun areTwoCardsUsersPut(): Boolean {
        return isPutCardLeft && isPutCardRight
    }

    fun compareCards(cardLeft: CardModel, cardRight: CardModel ): String? {

        return if (cardLeft.number!! > cardRight.number!!) {

            WIN_USER_LEFT

        } else if (cardLeft.number == cardRight.number) {

            if (cardLeft.type!! < cardRight.type!!) {
                WIN_USER_LEFT
            } else {
                WIN_USER_RIGHT
            }

        } else {
            WIN_USER_RIGHT
        }
    }


// Public methods - Rounds
// =================================================================================================

    fun getCounterRounds(): Int {
        return this.counterRounds
    }

    fun getCounterRWonUserLeft(): Int {
        return this.counterRWonUserLeft
    }

    fun getCounterRWonUserRight(): Int {
        return this.counterRWonUserRight
    }

    fun countRound() {
        this.counterRounds++
    }

    fun countRWonUserLeft() {
        this.counterRWonUserLeft++
    }

    fun countRWonUserRight() {
        this.counterRWonUserRight++
    }


// Public methods - Discards
// =================================================================================================

    fun getCounterDiscardsUserLeft(): Int {
        return this.counterDiscardsUserLeft
    }

    fun getCounterDiscardsUserRight(): Int {
        return this.counterDiscardsUserRight
    }

    fun countDiscardsUserLeft() {
        this.counterDiscardsUserLeft += 2
    }

    fun countDiscardsUserRight() {
        this.counterDiscardsUserRight += 2
    }
}