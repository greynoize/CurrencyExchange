package com.greynoize.base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greynoize.base.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}