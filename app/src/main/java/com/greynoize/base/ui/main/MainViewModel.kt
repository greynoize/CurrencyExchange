package com.greynoize.base.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greynoize.base.repository.network.CurrencyRepository
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.ui.base.BaseViewModel
import com.greynoize.base.ui.model.currency.CurrencyNameModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val currencyRepository: CurrencyRepository) : BaseViewModel() {
    lateinit var currenciesNames: Map<String, String>

    var currenciesList = MutableLiveData<MutableMap<CurrencyNameModel, Double>>()
        private set

    fun getCurrencies() {
        viewModelScope.launch {
            requestCurrencies()
        }
    }

    private suspend fun requestCurrencies() {
        val result = currencyRepository.getCurrencies("EUR", currenciesNames)

        when (result) {
            is Result.Success -> {
                currenciesList.postValue(result.value.rates.toMutableMap())
            }
        }

        delay(TIME_TO_WAIT_MS)
        requestCurrencies()
    }

    companion object {
        const val TIME_TO_WAIT_MS = 1000L
    }
}