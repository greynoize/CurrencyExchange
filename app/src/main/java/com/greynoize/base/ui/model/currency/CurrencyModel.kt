package com.greynoize.base.ui.model.currency

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.greynoize.base.Utils
import com.greynoize.base.repository.model.currency.CurrencyResponseModel

data class CurrencyModel(
    val baseCurrency: String,
    val rates: Map<CurrencyNameModel, Double>
)

data class CurrencyNameModel(
    val title: String,
    val fullName: String
)

fun currencyResponseToUIModel(response: CurrencyResponseModel, namesMap: Map<String, String>): CurrencyModel {
    val firstMap = response.rates

    val resultMap = firstMap.keys.intersect(namesMap.keys).associate { CurrencyNameModel(it, namesMap[it]!!) to firstMap[it]!! }

    return CurrencyModel(response.baseCurrency, resultMap)
}