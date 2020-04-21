package com.greynoize.base.repository.network.repositories

import com.greynoize.base.R
import com.greynoize.base.repository.model.currency.CurrencyInfoResponseModel
import com.greynoize.base.repository.model.currency.ExchangeRateResponseModel
import com.greynoize.base.repository.network.base.BaseRepository
import com.greynoize.base.repository.network.base.CurrenciesApi
import com.greynoize.base.repository.network.base.Result

class CurrencyRepository(private val currenciesApi: CurrenciesApi) : BaseRepository() {
    suspend fun getExchangeRates(base: String): Result<ExchangeRateResponseModel> {
        val result = getApiResult { currenciesApi.getCurrencies(base) }
        return result
    }

    /**
     * I've created this list because I didn't found an API with all currencies and codes for them.
     * APIs returns or one currencies per request, or list of countries with currencies (but some countries can have multiple currencies), or name only in English (but we need to carry about translation).
     * So i've decided to create this.
     * In real application the best solution (for my opinion) is to use an API with a language code, which will return link for a image and a translated name. The image can be downloaded using Glide or an other image library with a cache.
     */

    fun getCurrenciesInfo(): List<CurrencyInfoResponseModel> {
        return arrayListOf<CurrencyInfoResponseModel>().apply {
            add(CurrencyInfoResponseModel("AUD", R.string.aud, R.drawable.ic_australia))
            add(CurrencyInfoResponseModel("BGN", R.string.bgn, R.drawable.ic_bulgaria))
            add(CurrencyInfoResponseModel("BRL", R.string.brl, R.drawable.ic_brazil))
            add(CurrencyInfoResponseModel("CAD", R.string.cad, R.drawable.ic_canada))
            add(CurrencyInfoResponseModel("CHF", R.string.chf, R.drawable.ic_swiss))
            add(CurrencyInfoResponseModel("CNY", R.string.cny, R.drawable.ic_china))
            add(CurrencyInfoResponseModel("CZK", R.string.czk, R.drawable.ic_czech_republic))
            add(CurrencyInfoResponseModel("DKK", R.string.dkk, R.drawable.ic_denmark))
            add(CurrencyInfoResponseModel("GBP", R.string.gbp, R.drawable.ic_united_kingdom))
            add(CurrencyInfoResponseModel("HKD", R.string.hkd, R.drawable.ic_hong_kong))
            add(CurrencyInfoResponseModel("HRK", R.string.hrk, R.drawable.ic_croatia))
            add(CurrencyInfoResponseModel("HUF", R.string.huf, R.drawable.ic_hungary))
            add(CurrencyInfoResponseModel("IDR", R.string.idr, R.drawable.ic_indonesia))
            add(CurrencyInfoResponseModel("ILS", R.string.ils, R.drawable.ic_israel))
            add(CurrencyInfoResponseModel("INR", R.string.inr, R.drawable.ic_india))
            add(CurrencyInfoResponseModel("ISK", R.string.isk, R.drawable.ic_iceland))
            add(CurrencyInfoResponseModel("JPY", R.string.jpy, R.drawable.ic_japan))
            add(CurrencyInfoResponseModel("MXN", R.string.mxn, R.drawable.ic_mexico))
            add(CurrencyInfoResponseModel("KRW", R.string.krw, R.drawable.ic_south_korea))
            add(CurrencyInfoResponseModel("MYR", R.string.myr, R.drawable.ic_malaysia))
            add(CurrencyInfoResponseModel("NOK", R.string.nok, R.drawable.ic_norway))
            add(CurrencyInfoResponseModel("NZD", R.string.nzd, R.drawable.ic_new_zealand))
            add(CurrencyInfoResponseModel("PLN", R.string.pln, R.drawable.ic_republic_of_poland))
            add(CurrencyInfoResponseModel("PHP", R.string.php, R.drawable.ic_philippines))
            add(CurrencyInfoResponseModel("RON", R.string.ron, R.drawable.ic_romania))
            add(CurrencyInfoResponseModel("RUB", R.string.rub, R.drawable.ic_russia))
            add(CurrencyInfoResponseModel("SEK", R.string.sek, R.drawable.ic_sweden))
            add(CurrencyInfoResponseModel("SGD", R.string.sgd, R.drawable.ic_singapore))
            add(CurrencyInfoResponseModel("THB", R.string.thb, R.drawable.ic_thailand))
            add(CurrencyInfoResponseModel("USD", R.string.usd, R.drawable.ic_united_states_of_america))
            add(CurrencyInfoResponseModel("ZAR", R.string.zar, R.drawable.ic_south_africa))
            add(CurrencyInfoResponseModel("EUR", R.string.eur, R.drawable.ic_european_union))
        }
    }
}