package com.greynoize.base.ui.base.navigation

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import com.greynoize.base.ui.base.BaseFragment

class Navigator(private val activity: FragmentActivity, private val host: Int) {
    fun goTo(location: Location) {
        when (location) {
            is Location.Browse -> activity.startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(location.url) })
            is Location.Screen -> transaction(location.fragment)
        }
    }

    private fun transaction(fragment: BaseFragment<*>) = with(activity) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(host, fragment)
        transaction.addToBackStack(fragment::class.java.simpleName)
        transaction.commit()
    }
}