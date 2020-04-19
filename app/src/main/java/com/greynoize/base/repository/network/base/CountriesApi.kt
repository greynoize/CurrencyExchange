package com.greynoize.base.repository.network.base

import com.greynoize.base.repository.model.currency.ExchangeRateResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("rest/v2/all")
    suspend fun getCurrencies() : Response<ExchangeRateResponseModel>
}