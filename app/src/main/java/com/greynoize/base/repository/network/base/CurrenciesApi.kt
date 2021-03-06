package com.greynoize.base.repository.network.base

import com.greynoize.base.repository.model.currency.ExchangeRateResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {
    @GET("api/android/latest")
    suspend fun getCurrencies(@Query("base") base: String) : Response<ExchangeRateResponseModel>
}