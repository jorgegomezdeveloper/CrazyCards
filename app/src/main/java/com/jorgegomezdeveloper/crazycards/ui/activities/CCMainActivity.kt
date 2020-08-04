package com.jorgegomezdeveloper.crazycards.ui.activities

import android.os.Bundle
import com.jorgegomezdeveloper.crazycards.R
import com.jorgegomezdeveloper.crazycards.ui.base.CCBaseActivity
import com.jorgegomezdeveloper.crazycards.ui.base.CCBaseFragment
import com.jorgegomezdeveloper.crazycards.ui.features.gametable.view.CCGameTableFragment
import org.koin.android.ext.android.inject

class CCMainActivity: CCBaseActivity() {

// Constants
// =================================================================================================

    companion object {
        private const val TAG_ACTIVITY = "CCMainActivity"
    }


// Injects
// =================================================================================================

    private val ccGameTableFragment: CCGameTableFragment by inject()


// Config
// =================================================================================================

    override fun getActivityLayout(): Int {
        return R.layout.activity_cc_main
    }

    override fun getActivityTag(): String? {
        return CCMainActivity::class.java.simpleName
    }

    override fun getInitialFragment(): CCBaseFragment? {
       return ccGameTableFragment
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeListeners()
    }

    override fun initializeViews() {

    }

    override fun initializeListeners() {
    }
}