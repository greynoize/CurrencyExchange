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
import kotlin.collections.ArrayList

class MainViewModel(private val currencyRepository: CurrencyRepository) : BaseViewModel() {
    var baseCurrency = START_CURRENCY
        private set

    var count = 100

    private val infoList: List<CurrencyInfoResponseModel>

    var currenciesList = MutableLiveData<ArrayList<CurrencyUIModel>>()
        private set

    var positionChanged = false

    init {
        infoList = currencyRepository.getCurrenciesInfo()
        viewModelScope.launch {
            requestCurrencies()
        }
    }

    private suspend fun requestCurrencies() {
        // Pause when application on pause
        val result = currencyRepository.getExchangeRates(baseCurrency)

        when (result) {
            is Result.Success -> {
                if (currenciesList.value.isNullOrEmpty()) {
                    val list = arrayListOf<CurrencyUIModel>()

                    infoList.forEach {
                        if (it.code == baseCurrency) {
                            list.add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, null, 1.00))
                        } else {
                            list.add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, null, result.value.rates[it.code]))
                        }
                    }

                    currenciesList.postValue(list)
                } else {
                    val list = arrayListOf<CurrencyUIModel>().apply {
                        currenciesList.value!!.forEach {
                            add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, it.count, result.value.rates[it.code]))
                        }
                    }

                    currenciesList.postValue(list)
                }
            }
        }

        delay(TIME_TO_WAIT_MS)
        requestCurrencies()
    }

    fun onItemClick(item: CurrencyUIModel) {
        // Попробовать заменить тип на другой лист
        val items = arrayListOf<CurrencyUIModel>()
        items.add(item)

        val oldList = arrayListOf<CurrencyUIModel>().apply {
            addAll(currenciesList.value!!)
            remove(item)
        }

        items.addAll(oldList)

        baseCurrency = item.code
        positionChanged = true
        currenciesList.postValue(items)
    }

    companion object {
        const val TIME_TO_WAIT_MS = 1000L
        const val START_CURRENCY = "AUD"
    }
}