package com.greynoize.base.ui.model.currency

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter

data class CurrencyUIModel(
    val code: String,
    val nameResource: Int,
    val imageResource: Int,
    val count: Double?,
    var priceToBase: Double?
)

@BindingAdapter("imageView:srcFromResource")
fun ImageView.srcFromResource(resource: Int) {
    this.setImageResource(resource)
}


@BindingAdapter("editText:firstDouble", "editText:secondDouble")
fun EditText.multiplyDouble(firstDouble: Double, secondDouble: Double) {
    val value = (firstDouble * secondDouble)
    this.setText(String.format("%.2f", value))
}