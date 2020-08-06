package com.jorgegomezdeveloper.crazycards.data.services.play

import com.jorgegomezdeveloper.crazycards.model.CardModel

class PlayManager {

    companion object {

        const val WIN_USER_LEFT = "left"
        const val WIN_USER_RIGHT = "right"
    }

// Attributes
// =================================================================================================

    private var isPutCardLeft: Boolean = false
    private var isPutCardRight: Boolean = false

// Free Resources
// =================================================================================================

    fun freeResources() {

        this.isPutCardLeft = false
        this.isPutCardRight = false
    }

// Public methods
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

            if (cardLeft.type!! > cardRight.type!!) {
                WIN_USER_LEFT
            } else {
                WIN_USER_RIGHT
            }

        } else {
            WIN_USER_RIGHT
        }
    }

}