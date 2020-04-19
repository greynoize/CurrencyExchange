package com.greynoize.base.ui.model.currency

import com.greynoize.base.R

data class CurrencyUIModel(
    val code: String,
    val nameResource: Int,
    val imageResource: Int,
    val count: Double,
    val priceToBase: Double
)

/**
 * I've created this list because I didn't found an API with all currencies and codes for them.
 * APIs returns or one currencies per request, or list of countries with currencies (but some countries can have multiple currencies), or name only in English (but we need to carry about translation).
 * So i've decided to create this.
 * In real application the best solution (for my opinion) is to use an API with a language code, which will return link for a image and a translated name. The image can be downloaded using Glide or an other image library with a cache.
 */

fun getBaseCurrencies(): List<CurrencyUIModel> {
    return arrayListOf<CurrencyUIModel>().apply {
        add(CurrencyUIModel("AUD", R.string.aud, ))
    }
}