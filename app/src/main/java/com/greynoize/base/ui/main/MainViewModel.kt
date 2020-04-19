package com.greynoize.base.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greynoize.base.repository.model.currency.CurrencyInfoResponseModel
import com.greynoize.base.repository.network.repositories.CurrencyRepository
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.ui.base.BaseViewModel
import com.greynoize.base.ui.model.currency.CurrencyUIModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val currencyRepository: CurrencyRepository) : BaseViewModel() {
    var baseCurrency = START_CURRENCY

    private val currenciesInfo: List<CurrencyInfoResponseModel>

    var currenciesList = MutableLiveData<MutableList<CurrencyUIModel>>()
        private set

    init {
        currenciesInfo = currencyRepository.getCurrenciesInfo()
        viewModelScope.launch {
            requestCurrencies()
        }
    }

    private suspend fun requestCurrencies() {
        val result = currencyRepository.getExchangeRates(baseCurrency)

        when (result) {
            is Result.Success -> {
                val list = mutableListOf<CurrencyUIModel>()

                currenciesInfo.forEach {
                    list.add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, null, result.value.rates[it.code]))
                }

                currenciesList.postValue(list)
            }
        }
/*
        delay(TIME_TO_WAIT_MS)
        requestCurrencies()*/
    }

    fun onItemClick(item: CurrencyUIModel) {
        // Попробовать заменить тип на другой лист
        val items = mutableListOf<CurrencyUIModel>()
        items.add(item)

        val oldList = mutableListOf<CurrencyUIModel>().apply {
            addAll(currenciesList.value!!)
            remove(item)
        }

        items.addAll(oldList)
        currenciesList.postValue(items)
    }

    companion object {
        const val TIME_TO_WAIT_MS = 1000L
        const val START_CURRENCY = "AUD"
    }
}