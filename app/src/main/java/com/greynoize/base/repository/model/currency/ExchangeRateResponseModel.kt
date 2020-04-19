package com.greynoize.base.repository.model.currency


import com.google.gson.annotations.SerializedName

data class ExchangeRateResponseModel(
    @SerializedName("baseCurrency")
    val baseCurrency: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
)