package com.greynoize.base.ui.model.currency

import com.greynoize.base.R

data class CurrencyUIModel(
    val code: String,
    val nameResource: Int,
    val imageResource: Int,
    val count: Double?,
    val priceToBase: Double?
)

/**
 * I've created this list because I didn't found an API with all currencies and codes for them.
 * APIs returns or one currencies per request, or list of countries with currencies (but some countries can have multiple currencies), or name only in English (but we need to carry about translation).
 * So i've decided to create this.
 * In real application the best solution (for my opinion) is to use an API with a language code, which will return link for a image and a translated name. The image can be downloaded using Glide or an other image library with a cache.
 */

fun getBaseCurrencies(): List<CurrencyUIModel> {
    return arrayListOf<CurrencyUIModel>().apply {
        add(CurrencyUIModel("AUD", R.string.aud, R.drawable.ic_australia, null, null))
        add(CurrencyUIModel("BGN", R.string.bgn, R.drawable.ic_bulgaria, null, null))
        add(CurrencyUIModel("BRL", R.string.brl, R.drawable.ic_brazil, null, null))
        add(CurrencyUIModel("CAD", R.string.cad, R.drawable.ic_canada, null, null))
        add(CurrencyUIModel("CHF", R.string.chf, R.drawable.ic_sweden, null, null))
        add(CurrencyUIModel("CNY", R.string.cny, R.drawable.ic_china, null, null))
        add(CurrencyUIModel("DKK", R.string.dkk, R.drawable.ic_denmark, null, null))
        add(CurrencyUIModel("GBP", R.string.gbp, R.drawable.ic_united_kingdom, null, null))
        add(CurrencyUIModel("HKD", R.string.hkd, R.drawable.ic_hong_kong, null, null))
        add(CurrencyUIModel("HRK", R.string.hrk, R.drawable.ic_croatia, null, null))
        add(CurrencyUIModel("HUF", R.string.huf, R.drawable.ic_hungary, null, null))
        add(CurrencyUIModel("IDR", R.string.hkd, R.drawable.ic_india, null, null))
        add(CurrencyUIModel("ILS", R.string.ils, R.drawable.ic_israel, null, null))
        add(CurrencyUIModel("ISK", R.string.isk, R.drawable.ic_iceland, null, null))
        add(CurrencyUIModel("JPY", R.string.jpy, R.drawable.ic_japan, null, null))
        add(CurrencyUIModel("MXN", R.string.mxn, R.drawable.ic_mexico, null, null))
        add(CurrencyUIModel("KRW", R.string.krw, R.drawable.ic_south_korea, null, null))
        add(CurrencyUIModel("MYR", R.string.myr, R.drawable.ic_malaysia, null, null))
        add(CurrencyUIModel("NOK", R.string.nok, R.drawable.ic_norway, null, null))
        add(CurrencyUIModel("NZD", R.string.nzd, R.drawable.ic_new_zealand, null, null))
        add(CurrencyUIModel("PHP", R.string.php, R.drawable.ic_philippines, null, null))
        add(CurrencyUIModel("RON", R.string.ron, R.drawable.ic_romania, null, null))
        add(CurrencyUIModel("RUB", R.string.rub, R.drawable.ic_russia, null, null))
        add(CurrencyUIModel("SEK", R.string.sek, R.drawable.ic_sweden, null, null))
        add(CurrencyUIModel("SGD", R.string.sgd, R.drawable.ic_singapore, null, null))
        add(CurrencyUIModel("USD", R.string.usd, R.drawable.ic_united_states_of_america, null, null))
        add(CurrencyUIModel("ZAR", R.string.zar, R.drawable.ic_south_africa, null, null))
        add(CurrencyUIModel("EUR", R.string.eur, R.drawable.ic_european_union, null, null))
    }
}