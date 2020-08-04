package com.jorgegomezdeveloper.crazycards.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jorgegomezdeveloper.crazycards.R

abstract class CCBaseActivity: AppCompatActivity() {

// Config
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())

        loadInitialFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
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

    /**
     * Load the fragments for the navigation.
     */
    open fun navigateTo(fragment: CCBaseFragment, addToBackStack: Boolean) {

        if (addToBackStack) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment, fragment.getFragmentTag())
                .addToBackStack(fragment.getFragmentTag())
                .commit()

        } else {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment, fragment.getFragmentTag())
                .commit()
        }
    }

// To be override...
// =================================================================================================

    protected open fun initializeViews() {}

    protected open fun initializeListeners() {}

    protected open fun loadData() {}

    protected abstract fun getActivityLayout(): Int

    protected abstract fun getActivityTag(): String?


// Abstract methods
// =================================================================================================

    /**
     * Return null if the extension have not to implement a [CCBaseFragment]
     */
    protected abstract fun getInitialFragment(): CCBaseFragment?

    protected abstract fun initialize()
}