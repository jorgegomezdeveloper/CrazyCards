package com.jorgegomezdeveloper.crazycards.ui.features.gametable.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jorgegomezdeveloper.crazycards.data.services.cards.CardsManager
import com.jorgegomezdeveloper.crazycards.model.CardModel
import com.jorgegomezdeveloper.crazycards.ui.base.CCBaseViewModel

class CCGameTableViewModel: CCBaseViewModel() {

    // Cards Lef and Right Put
    var cardLefPut: CardModel? = null
    var cardRightPut: CardModel? = null

    // Mutable live data for lists of cards.

    private var buildListOrderedMutableLiveData:
                MutableLiveData<ArrayList<CardModel?>> = MutableLiveData()
    private var buildListRandomMutableLiveData:
                MutableLiveData<ArrayList<CardModel?>> = MutableLiveData()
    private var buildListRandomLeftMutableLiveData:
                MutableLiveData<ArrayList<CardModel?>> = MutableLiveData()
    private var buildListRandomRightMutableLiveData:
                MutableLiveData<ArrayList<CardModel?>> = MutableLiveData()

    // Builders of lists for cards.

    fun buildListOrdered(cardsManager: CardsManager) {
        buildListOrderedMutableLiveData.value = cardsManager.buildListOrdered()
    }

    fun buildListRandom(cardsManager: CardsManager, cardListOrdered: ArrayList<CardModel?>?) {
        buildListRandomMutableLiveData.value = cardsManager.buildListRandom(cardListOrdered)
    }

    fun buildListRandomLeft(cardsManager: CardsManager, cardListRandom: ArrayList<CardModel?>?) {
        buildListRandomLeftMutableLiveData.value = cardsManager.buildListRandomLeft(cardListRandom)
    }

    fun buildListRandomRight(cardsManager: CardsManager, cardListRandom: ArrayList<CardModel?>?) {
        buildListRandomRightMutableLiveData.value = cardsManager.buildListRandomRight(cardListRandom)
    }

    // Gets Mutable live data.

    fun getBuildListOrderedMutableLiveData(): MutableLiveData<ArrayList<CardModel?>> {
        return buildListOrderedMutableLiveData
    }

    fun getBuildListRandomMutableLiveData(): MutableLiveData<ArrayList<CardModel?>> {
        return buildListRandomMutableLiveData
    }

    fun getBuildListRandomLeftMutableLiveData(): MutableLiveData<ArrayList<CardModel?>> {
        return buildListRandomLeftMutableLiveData
    }

    fun getBuildListRandomRightMutableLiveData(): MutableLiveData<ArrayList<CardModel?>> {
        return buildListRandomRightMutableLiveData
    }
}