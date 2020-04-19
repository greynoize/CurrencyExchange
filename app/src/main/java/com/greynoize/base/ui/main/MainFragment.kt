package com.greynoize.base.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.greynoize.base.Const
import com.greynoize.base.R
import com.greynoize.base.ui.base.BaseFragment
import com.greynoize.base.ui.main.adapter.MainCurrenciesDiffUtilCallback
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {
    override val layout = R.layout.fragment_main
    override val fragmentViewModel by viewModel<MainViewModel>()

    private var adapter = MainCurrenciesAdapter() {
        fragmentViewModel.onItemClick(it)
    }

    private lateinit var layoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initViewModelData()
    }

    private fun initViewModelData() {
        fragmentViewModel.currenciesList.observe(viewLifecycleOwner, Observer {
            val data = it ?: return@Observer

            val diffUtilCallback = MainCurrenciesDiffUtilCallback(adapter.items, it)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)

            adapter.items = data
            diffUtilResult.dispatchUpdatesTo(adapter)
            main_list.scrollToPosition(0)
        })
    }

    private fun initUI() {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        main_list.adapter = adapter
        main_list.layoutManager = layoutManager
    }
}