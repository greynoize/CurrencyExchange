package com.greynoize.base.repository.network

import android.app.Application
import com.greynoize.base.repository.network.base.Api
import com.greynoize.base.repository.network.base.BaseRepository
import com.greynoize.base.repository.network.base.Result
import com.greynoize.base.ui.model.currency.CurrencyModel
import com.greynoize.base.ui.model.currency.currencyResponseToUIModel

class CurrencyRepository(private val api: Api) : BaseRepository() {
    suspend fun getCurrencies(base: String, names: Map<String, String>): Result<CurrencyModel> {
        val result = getApiResult { api.getCurrencies(base) }

        return when (result) {
            is Result.Success -> {
                Result.Success(currencyResponseToUIModel(result.value, names))
            }

            is Result.Fail -> Result.Fail(result.value)
        }
    }
}