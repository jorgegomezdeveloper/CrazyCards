package com.jorgegomezdeveloper.crazycards.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class CCBaseActivity: AppCompatActivity() {

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

    protected open fun initializeViews() {}

    protected open fun loadData() {}

    protected abstract fun getActivityLayout(): Int

    open fun loadInitialFragment() {

    }

    open fun navigateTo(fragment: CCBaseFragment, addToBackStack: Boolean) {

    }
}