package com.greynoize.base.ui.model.currency

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.greynoize.base.R

data class CurrencyUIModel(
    val code: String,
    val nameResource: Int,
    val imageResource: Int,
    val count: Double?,
    val priceToBase: Double?
)

@BindingAdapter("imageView:srcFromResource")
fun ImageView.srcFromResource(resource: Int) {
    this.setImageResource(resource)
}