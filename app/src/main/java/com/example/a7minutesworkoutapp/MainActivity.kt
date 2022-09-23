package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var flStart: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setOnClick()
    }

    private fun initializeViews() {
        flStart = findViewById(R.id.fl_activity_main_start)
    }

    private fun setOnClick() {
        flStart?.setOnClickListener {
            Snackbar.make(
                findViewById(R.id.fl_activity_main_start),
                "Button touched",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}