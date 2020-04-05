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
import com.greynoize.base.ui.first.FirstViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment<VM : BaseViewModel>: Fragment() {
    private var binding: ViewDataBinding? = null

    protected abstract fun layout(): Int
    protected lateinit var navigator: Navigator
    protected val fragmentViewModel: FirstViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false)
        binding?.lifecycleOwner = this
        return binding?.root
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

    override fun onDestroyView() {
        binding?.unbind()
        binding = null
        super.onDestroyView()
    }
}