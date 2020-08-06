package com.jorgegomezdeveloper.crazycards.model

/**
 * @author Jorge Gomez Alvarez (jorgegomezdeveloper@gmail.com)
 * This model allows to store the data of each card.
 */
data class CardModel(
    val id: String?,
    val type: Int?,
    val number: Int?,
    val symbol: String?
)