package com.greynoize.base.ui.base

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun View.visibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageView:srcFromResource")
fun ImageView.srcFromResource(resource: Int) {
    this.setImageResource(resource)
}