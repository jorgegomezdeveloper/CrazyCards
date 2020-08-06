package com.jorgegomezdeveloper.crazycards.data.services.play

import com.jorgegomezdeveloper.crazycards.common.Constants
import com.jorgegomezdeveloper.crazycards.model.CardModel

/**
 * @author Jorge Gomez Alvarez (jorgegomezdeveloper@gmail.com)
 * This manager class allows you to implement the necessary methods
 * to compare cards, control results, counters and game rounds.
 */
class PlayManager {

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

    /**
     * It allows you to check if both users have already placed their cards.
     */
    fun areTwoCardsUsersPut(): Boolean {
        return isPutCardLeft && isPutCardRight
    }

    /**
     * It allows you to compare the values ​​of the cards in play. First,
     * the highest card number is checked, and the highest number wins,
     * and if they are equal numbers, then the type of card is checked,
     * and the highest priority wins.
     */
    fun compareCards(cardLeft: CardModel, cardRight: CardModel ): String? {

        return if (cardLeft.number!! > cardRight.number!!) {

            Constants.WIN_USER_LEFT

        } else if (cardLeft.number == cardRight.number) {

            if (cardLeft.type!! < cardRight.type!!) {
                Constants.WIN_USER_LEFT
            } else {
                Constants.WIN_USER_RIGHT
            }

        } else {
            Constants.WIN_USER_RIGHT
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

    /**
     * Allows you to count one more round.
     */
    fun countRound() {
        this.counterRounds++
    }

    /**
     * Allows you to count one more round won by the user on the left.
     */
    fun countRWonUserLeft() {
        this.counterRWonUserLeft++
    }

    /**
     * Allows you to count one more round won by the user on the right.
     */
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

    /**
     * Allows you to count two more cards discarded for the user on the left.
     */
    fun countDiscardsUserLeft() {
        this.counterDiscardsUserLeft += 2
    }

    /**
     * Allows you to count two more cards discarded for the user on the right.
     */
    fun countDiscardsUserRight() {
        this.counterDiscardsUserRight += 2
    }
}