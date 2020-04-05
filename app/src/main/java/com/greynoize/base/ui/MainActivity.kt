package com.greynoize.base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greynoize.base.R
import com.greynoize.base.ui.base.navigation.Location
import com.greynoize.base.ui.base.navigation.Navigator
import com.greynoize.base.ui.first.FirstScreenFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}