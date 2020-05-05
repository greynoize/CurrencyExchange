package com.greynoize.base.repository.network.repositories

import com.greynoize.base.repository.model.currency.CurrencyInfoResponseModel
import com.greynoize.base.repository.model.currency.ExchangeRateResponseModel
import com.greynoize.base.repository.network.base.Result

interface CurrencyRepository {
    suspend fun getExchangeRates(base: String): Result<ExchangeRateResponseModel>
    fun getCurrenciesInfo(): List<CurrencyInfoResponseModel>
}