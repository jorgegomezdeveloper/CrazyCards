package com.jorgegomezdeveloper.crazycards.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jorgegomezdeveloper.crazycards.R
abstract class CCBaseFragment: Fragment() {


// Attributes
// =================================================================================================

    /**
     * Life cycle of the activities.
     */
    private var mContext: Context? = null


// To be override...
// =================================================================================================

    protected open fun initializeViews() {}

    protected open fun initializeListeners() {}

    protected open fun loadData() {}

    protected open fun updateView() {}

    protected abstract fun getFragmentLayout(): Int

    abstract fun getFragmentTag(): String?

    protected abstract fun initialize()

// Config
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(getFragmentLayout(), container, false)

        if (container != null) {
            mContext = container.context
        }

        initializeViews()
        initializeListeners()
        loadData()

        return rootView
    }

// Public methods
// =================================================================================================

    /**
     * Load the fragments for the navigation.
     */
    open fun navigateTo(fragment: CCBaseFragment, addToBackStack: Boolean) {

        if (activity?.supportFragmentManager != null) {
            if (addToBackStack) {

                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment, fragment.getFragmentTag())
                    .addToBackStack(fragment.getFragmentTag())
                    .commit()

            } else {

                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment, fragment.getFragmentTag())
                    .commit()
            }
        }
    }
}