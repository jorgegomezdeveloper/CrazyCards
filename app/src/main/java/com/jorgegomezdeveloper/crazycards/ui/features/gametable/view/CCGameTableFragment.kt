package com.jorgegomezdeveloper.crazycards.ui.features.gametable.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.jorgegomezdeveloper.crazycards.R
import com.jorgegomezdeveloper.crazycards.data.services.cards.CardsManager
import com.jorgegomezdeveloper.crazycards.databinding.FragmentCcGameTableBinding
import com.jorgegomezdeveloper.crazycards.ui.base.CCBaseViewModelFragment
import com.jorgegomezdeveloper.crazycards.ui.features.gametable.viewmodel.CCGameTableViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CCGameTableFragment: CCBaseViewModelFragment<CCGameTableViewModel>() {

    companion object {
        const val TAG_FRAGMENT = "CCGameTableFragment"
    }

    // DEPENDENCY INJECTS

    // View Models and Fragments
    private val ccGameTableViewModel: CCGameTableViewModel by viewModel()

    // Managers
    private val cardsManager: CardsManager by inject()

    // View Binding
    private lateinit var binding: FragmentCcGameTableBinding

    override fun getFragmentTag(): String? {
        return TAG_FRAGMENT
    }

    override fun initialize() {

    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCcGameTableBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_cc_game_table
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    override fun loadData() {


    }

}