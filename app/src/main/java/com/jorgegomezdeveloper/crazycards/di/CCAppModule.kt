package com.jorgegomezdeveloper.crazycards.di

import com.jorgegomezdeveloper.crazycards.data.services.cards.CardsManager
import com.jorgegomezdeveloper.crazycards.data.services.play.PlayManager
import com.jorgegomezdeveloper.crazycards.ui.features.gametable.view.CCGameTableFragment
import com.jorgegomezdeveloper.crazycards.ui.features.gametable.viewmodel.CCGameTableViewModel
import com.jorgegomezdeveloper.crazycards.util.DataStorageUtil
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


// FRAGMENT modules
// =================================================================================================
val ccFragmentsModule = module {
        factory { CCGameTableFragment() }
}

// VIEW MODEL modules
// =================================================================================================
val ccViewModelsModule = module {
        viewModel { CCGameTableViewModel() }
}

// UTILS modules
// =================================================================================================

val ccUtilsModule = module {
        single { DataStorageUtil(get()) }
}

// Manager modules
// =================================================================================================

val ccManagerModule = module {
        single { CardsManager() }
        single { PlayManager() }
}

