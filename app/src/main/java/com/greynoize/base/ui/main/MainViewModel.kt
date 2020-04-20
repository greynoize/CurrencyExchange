package com.greynoize.base.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greynoize.base.repository.model.currency.CurrencyInfoResponseModel
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.repository.network.repositories.CurrencyRepository
import com.greynoize.base.ui.base.BaseViewModel
import com.greynoize.base.ui.model.currency.CurrencyUIModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val currencyRepository: CurrencyRepository) : BaseViewModel() {
    var baseCurrency = START_CURRENCY
        private set

    var count = DEFAULT_COUNT_VALUE

    var currenciesList = MutableLiveData<MutableList<CurrencyUIModel>>()
        private set

    var positionChanged = false

    private val infoList: List<CurrencyInfoResponseModel> = currencyRepository.getCurrenciesInfo()

    init {
        viewModelScope.launch {
            requestCurrencies()
        }
    }

    private suspend fun requestCurrencies() {
        // Pause when application on pause
        val result = currencyRepository.getExchangeRates(baseCurrency)

        when (result) {
            is Result.Success -> {
                if (result.value.baseCurrency != baseCurrency) {
                    requestCurrencies()
                    return
                }

                if (currenciesList.value.isNullOrEmpty()) {
                    val list = arrayListOf<CurrencyUIModel>()

                    infoList.forEach {
                        if (it.code == baseCurrency) {
                            list.add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, count, BASE_EXCHANGE_RATE))
                        } else {
                            val currencyRate = result.value.rates[it.code] ?: 0.00

                            list.add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, count * currencyRate, currencyRate))
                        }
                    }

                    currenciesList.postValue(list)
                } else {
                    currenciesList.value!!.forEach {
                        if (it.code != baseCurrency) {
                            val newRate = result.value.rates[it.code] ?: 0.00
                            val newTotal = newRate * count

                            it.priceToBase = newRate
                            it.total = newTotal
                        }
                    }

                    currenciesList.postValue(currenciesList.value)
                }
            }
        }

        delay(TIME_TO_WAIT_MS)
        requestCurrencies()
    }

    fun onItemClick(item: CurrencyUIModel) {
        baseCurrency = item.code

        val items = arrayListOf<CurrencyUIModel>()
        items.add(item)

        currenciesList.value!!.remove(item)
        items.addAll(currenciesList.value!!)

        items.forEach {
            if (it.code == baseCurrency) {
                it.priceToBase = BASE_EXCHANGE_RATE
            } else {
                it.priceToBase = it.priceToBase / item.priceToBase
            }
        }

        positionChanged = true
        count = item.total
        currenciesList.postValue(items)
    }

    fun updateCount(enteredValue: Double) {
        count = enteredValue

        currenciesList.value!!.forEach {
            if (it.code == baseCurrency) {
                it.total = count
            } else {
                it.total = enteredValue * it.priceToBase
            }
        }

        currenciesList.postValue(currenciesList.value!!)
    }

    companion object {
        const val TIME_TO_WAIT_MS = 1000L
        const val START_CURRENCY = "AUD"
        const val DEFAULT_COUNT_VALUE = 100.00
        const val BASE_EXCHANGE_RATE = 1.00
    }
}