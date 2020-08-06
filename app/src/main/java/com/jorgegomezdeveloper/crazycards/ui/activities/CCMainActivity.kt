package com.jorgegomezdeveloper.crazycards.ui.activities

import android.os.Bundle
import com.jorgegomezdeveloper.crazycards.R
import com.jorgegomezdeveloper.crazycards.ui.base.CCBaseActivity
import com.jorgegomezdeveloper.crazycards.ui.base.CCBaseFragment
import com.jorgegomezdeveloper.crazycards.ui.features.gametable.view.CCGameTableFragment
import org.koin.android.ext.android.inject

/**
 * @author Jorge Gomez Alvarez (jorgegomezdeveloper@gmail.com)
 * This class is the main activity. And load the fragment of the game board.
 */
class CCMainActivity: CCBaseActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}