package com.greynoize.base.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.greynoize.base.ui.model.currency.CurrencyUIModel

class MainCurrenciesDiffUtilCallback(private val oldList: List<CurrencyUIModel>, private val newList: List<CurrencyUIModel>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.code == newItem.code
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.code == newItem.code && oldItem.priceToBase == newItem.priceToBase
    }
}