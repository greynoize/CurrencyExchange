package com.greynoize.base.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.greynoize.base.R
import com.greynoize.base.ui.base.navigation.Navigator

abstract class BaseFragment: Fragment() {
    private var binding: ViewDataBinding? = null

    protected abstract val layout: Int
    protected abstract val fragmentViewModel: BaseViewModel
    protected lateinit var navigator: Navigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return getBinding(inflater, container)?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDataListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDataListeners() {
        navigator = Navigator(requireActivity(), R.id.main_host)

        fragmentViewModel.navigate.observe(viewLifecycleOwner, Observer {
            val location = it ?: return@Observer
            navigator.goTo(location)
            fragmentViewModel.navigate.value = null
        })
    }

    protected fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding?.lifecycleOwner = this
        return binding
    }

    override fun onDestroyView() {
        binding?.unbind()
        binding = null
        super.onDestroyView()
    }
}