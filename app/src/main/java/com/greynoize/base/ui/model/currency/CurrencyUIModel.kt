package com.greynoize.base.ui.model.currency

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter

data class CurrencyUIModel(
    val code: String,
    val nameResource: Int,
    val imageResource: Int,
    var total: Double,
    var priceToBase: Double
)

@BindingAdapter("imageView:srcFromResource")
fun ImageView.srcFromResource(resource: Int) {
    this.setImageResource(resource)
}


@BindingAdapter("editText:count", "editText:item")
fun EditText.multiplyDouble(firstDouble: Double, item: CurrencyUIModel) {
    val value = (firstDouble * (item.priceToBase ?: 0.00))
    this.setText(String.format("%.2f", value))
}