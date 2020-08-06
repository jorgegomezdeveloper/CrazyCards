package com.jorgegomezdeveloper.crazycards.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jorgegomezdeveloper.crazycards.R

/**
 * @author Jorge Gomez Alvarez (jorgegomezdeveloper@gmail.com)
 * This parent class of all activities.
 */
abstract class CCBaseActivity: AppCompatActivity() {

// Config
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())

        loadInitialFragment()
    }

// Public methods
// =================================================================================================

    /**
     * Load the initial fragment.
     */
    open fun loadInitialFragment() {

        val initialFragment: CCBaseFragment? = getInitialFragment()

        if (initialFragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, initialFragment, initialFragment.getFragmentTag())
                .commit()
        }
    }

// To be override...
// =================================================================================================

    protected abstract fun getActivityLayout(): Int

    protected abstract fun getActivityTag(): String?


// Abstract methods
// =================================================================================================

    protected abstract fun getInitialFragment(): CCBaseFragment?
}