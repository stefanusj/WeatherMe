package com.stefanusj.weatherme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stefanusj.weatherme.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}