package com.greynoize.base.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greynoize.base.repository.model.currency.CurrencyInfoResponseModel
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.repository.network.repositories.CurrencyRepository
import com.greynoize.base.ui.base.BaseViewModel
import com.greynoize.base.ui.model.currency.CurrencyUIModel
import kotlinx.coroutines.launch

class MainViewModel(private val currencyRepository: CurrencyRepository) : BaseViewModel() {
    var baseCurrency = START_CURRENCY
        private set

    var count = DEFAULT_COUNT_VALUE

    private val infoList: List<CurrencyInfoResponseModel>

    var currenciesList = MutableLiveData<MutableList<CurrencyUIModel>>()
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
                if (result.value.baseCurrency != baseCurrency) return

                if (currenciesList.value.isNullOrEmpty()) {
                    val list = arrayListOf<CurrencyUIModel>()

                    infoList.forEach {
                        if (it.code == baseCurrency) {
                            list.add(
                                CurrencyUIModel(
                                    it.code,
                                    it.nameResource,
                                    it.imageResource,
                                    count,
                                    1.00
                                )
                            )
                        } else {
                            val currencyRate = result.value.rates[it.code] ?: 0.00
                            list.add(
                                CurrencyUIModel(
                                    it.code,
                                    it.nameResource,
                                    it.imageResource,
                                    count * currencyRate,
                                    currencyRate
                                )
                            )
                        }
                    }

                    currenciesList.postValue(list)
                } else {
                    val list = arrayListOf<CurrencyUIModel>().apply {
                        currenciesList.value!!.forEach {
                            add(
                                CurrencyUIModel(
                                    it.code,
                                    it.nameResource,
                                    it.imageResource,
                                    it.total,
                                    result.value.rates[it.code] ?: 0.00
                                )
                            )
                        }
                    }

                    currenciesList.postValue(list)
                }
            }
        }

/*        delay(TIME_TO_WAIT_MS)
        requestCurrencies()*/
    }

    fun onItemClick(item: CurrencyUIModel) {
        baseCurrency = item.code

        val itemRate = item.priceToBase
        val items = arrayListOf<CurrencyUIModel>()
        items.add(item)

        val oldList = arrayListOf<CurrencyUIModel>().apply {
            currenciesList.value!!.forEach {
                add(
                    CurrencyUIModel(
                        it.code,
                        it.nameResource,
                        it.imageResource,
                        it.total,
                        it.priceToBase
                    )
                )
            }

            remove(item)
        }

        items.addAll(oldList)

        items.forEach {
            if (it.code == baseCurrency) {
                it.priceToBase = 1.00
            } else {
                it.priceToBase = (it.priceToBase) / itemRate
            }
        }

        positionChanged = true
        count = item.total
        currenciesList.postValue(items)
        //count.postValue(item.total)
    }

    fun updateCount(enteredValue: Double, item: CurrencyUIModel) {
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
    }
}