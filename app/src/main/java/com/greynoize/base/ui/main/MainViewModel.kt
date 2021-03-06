package com.greynoize.base.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greynoize.base.repository.model.currency.CurrencyInfoResponseModel
import com.greynoize.base.repository.model.currency.ExchangeRateResponseModel
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.repository.network.repositories.CurrencyRepository
import com.greynoize.base.repository.network.repositories.CurrencyRepositoryImpl
import com.greynoize.base.ui.base.BaseViewModel
import com.greynoize.base.ui.model.currency.CurrencyUIModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
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

    private var requestJob: Job? = null

    init {
        loading.postValue(true)
    }

    private var canSendRepeatableRequest = true

    fun requestCurrencies() {
        canSendRepeatableRequest = true

        requestJob = viewModelScope.launch {
            try {
                val result = currencyRepository.getExchangeRates(baseCurrency)

                when (result) {
                    is Result.Success -> {
                        if (loading.value == true) {
                            loading.postValue(false)
                        }

                        if (result.value.baseCurrency != baseCurrency) {
                            requestCurrencies()
                            return@launch
                        }

                        handleSuccess(result.value)
                    }
                }

                delay(TIME_TO_WAIT_MS)
                requestJob?.cancelAndJoin()
            } finally {
                if (canSendRepeatableRequest) {
                    requestCurrencies()
                }
            }
        }
    }

    fun cancelRequest(cancelRepeat: Boolean) {
        viewModelScope.launch {
            canSendRepeatableRequest = !cancelRepeat
            requestJob?.cancel()
        }
    }

    private fun handleSuccess(result: ExchangeRateResponseModel) {
        if (currenciesList.value.isNullOrEmpty()) {
            val list = arrayListOf<CurrencyUIModel>()

            infoList.forEach {
                if (it.code == baseCurrency) {
                    list.add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, count, BASE_EXCHANGE_RATE))
                } else {
                    val currencyRate = result.rates[it.code] ?: 0.00
                    list.add(CurrencyUIModel(it.code, it.nameResource, it.imageResource, count * currencyRate, currencyRate))
                }
            }

            currenciesList.postValue(list)
        } else {
            currenciesList.value!!.forEach {
                if (it.code != baseCurrency) {
                    it.priceToBase = result.rates[it.code] ?: 0.00
                    it.total = it.priceToBase * count
                }
            }

            currenciesList.postValue(currenciesList.value)
        }
    }

    fun onItemClick(item: CurrencyUIModel) {
        baseCurrency = item.code
        currenciesList.value!!.remove(item)

        val items = arrayListOf<CurrencyUIModel>()
        items.add(item)
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
        cancelRequest(false)
    }

    fun updateCount(enteredValue: Double) : Double {
        count = enteredValue

        currenciesList.value!!.forEach {
            if (it.code == baseCurrency) {
                it.total = count
            } else {
                it.total = enteredValue * it.priceToBase
            }
        }

        currenciesList.postValue(currenciesList.value!!)
        return count
    }

    companion object {
        const val TIME_TO_WAIT_MS = 1000L
        const val START_CURRENCY = "AUD"
        const val DEFAULT_COUNT_VALUE = 100.00
        const val BASE_EXCHANGE_RATE = 1.00
    }
}