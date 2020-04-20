package com.greynoize.base.ui.model.currency

data class CurrencyUIModel(
    val code: String,
    val nameResource: Int,
    val imageResource: Int,
    var total: Double,
    var priceToBase: Double
)
