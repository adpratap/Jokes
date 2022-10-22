package com.noreplypratap.randomuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noreplypratap.randomuser.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}