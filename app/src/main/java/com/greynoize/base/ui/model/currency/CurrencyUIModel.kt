package com.greynoize.base.ui.model.currency

import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.greynoize.base.ui.main.MainViewModel

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


@BindingAdapter("editText:firstDouble", "editText:item")
fun EditText.multiplyDouble(firstDouble: Double, item: CurrencyUIModel) {
    val value = (firstDouble * (item.priceToBase ?: 0.00))
    this.setText(String.format("%.2f", value))
}

@BindingAdapter("editText:textChanged", "editText:viewModel")
fun EditText.textChanged(item: CurrencyUIModel, viewModel: MainViewModel) {
    this.addTextChangedListener {
        if (it != this.text && this.isFocused) {
            viewModel.updateCount(it.toString().toDouble(), item)
            Log.d("TAAG", it.toString() + item.toString())
        }
    }
}