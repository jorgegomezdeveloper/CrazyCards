package com.jorgegomezdeveloper.crazycards.di

import com.jorgegomezdeveloper.crazycards.ui.features.gametable.view.CCGameTableFragment
import com.jorgegomezdeveloper.crazycards.ui.features.gametable.viewmodel.CCGameTableViewModel
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
