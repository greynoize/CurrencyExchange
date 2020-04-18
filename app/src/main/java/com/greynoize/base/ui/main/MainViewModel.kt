package com.greynoize.base.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greynoize.base.Const
import com.greynoize.base.repository.network.CurrencyRepository
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.ui.base.BaseViewModel
import com.greynoize.base.ui.model.currency.CurrencyModel
import com.greynoize.base.ui.model.currency.CurrencyNameModel
import kotlinx.coroutines.launch

class MainViewModel(private val currencyRepository: CurrencyRepository) : BaseViewModel() {
    lateinit var currenciesNames: Map<String, String>

    var currenciesList = MutableLiveData<MutableMap<CurrencyNameModel, Double>>()
        private set

    fun getCurrencies() {
        viewModelScope.launch {
            val result = currencyRepository.getCurrencies("EUR", currenciesNames)

            when (result) {
                is Result.Success -> {
                    currenciesList.postValue(result.value.rates.toMutableMap())
                }
                else -> {

                }
            }
            Log.d(Const.TAG, result.toString())
        }
    }
}