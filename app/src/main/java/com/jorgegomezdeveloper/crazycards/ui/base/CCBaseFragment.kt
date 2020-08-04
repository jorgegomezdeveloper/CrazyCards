package com.jorgegomezdeveloper.crazycards.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class CCBaseFragment: Fragment() {

    private var mContext: Context? = null

    protected open fun initializeViews() {}

    protected open fun initializeListeners() {}

    protected open fun loadData() {}

    protected open fun updateView() {}

    protected abstract fun getFragmentLayout(): Int

    protected abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onResume() {
        super.onResume()
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
}