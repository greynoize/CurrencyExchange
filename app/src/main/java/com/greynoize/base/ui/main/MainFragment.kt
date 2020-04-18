package com.greynoize.base.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.greynoize.base.Const
import com.greynoize.base.R
import com.greynoize.base.Utils
import com.greynoize.base.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {
    override val layout = R.layout.fragment_main
    override val fragmentViewModel by viewModel<MainViewModel>()

    private var adapter = MainCurrenciesAdapter()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initViewModelData()
    }

    private fun initViewModelData() {
        fragmentViewModel.currenciesNames = getCurrenciesNames()
        fragmentViewModel.getCurrencies()

        fragmentViewModel.currenciesList.observe(viewLifecycleOwner, Observer {
            val data = it ?: return@Observer

        })
    }

    private fun initUI() {
        main_list.adapter = adapter
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun getCurrenciesNames(): Map<String, String> {
        val json = Utils.getJsonStringFromFile(Const.CURRENCIES_NAMES_JSON_PATH, requireContext())
        return Gson().fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
    }
}