package com.greynoize.base.ui.base.navigation

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController

class Navigator(private val activity: FragmentActivity, private val host: Int) {
    fun goTo(location: Location) {
        when (location) {
            is Location.Browse -> activity.startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(location.url) })
            is Location.Screen -> transaction(location.id)
        }
    }

    private fun transaction(id: Int) = with(activity) {
        findNavController(host).navigate(id)
    }
}