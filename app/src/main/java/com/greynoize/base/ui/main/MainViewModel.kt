package com.greynoize.base.ui.main

import androidx.lifecycle.MutableLiveData
import com.greynoize.base.repository.network.repositories.CurrencyRepository
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.ui.base.BaseViewModel
import com.greynoize.base.ui.model.currency.CurrencyNameModel
import kotlinx.coroutines.delay

class MainViewModel(private val currencyRepository: CurrencyRepository) : BaseViewModel() {
    lateinit var currenciesNames: Map<String, String>

    var currenciesList = MutableLiveData<MutableMap<CurrencyNameModel, Double>>()
        private set

    fun getCurrencies() {
        /* TODO
        Ебани вместо всего этого дерьма обычный файл
         */
/*        viewModelScope.launch {
            requestCurrencies()
        }*/
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