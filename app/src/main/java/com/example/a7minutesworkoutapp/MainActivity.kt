package com.example.a7minutesworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkoutapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var mActivity: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setOnClick()
    }

    private fun setOnClick() {
        mActivity?.flActivityMainStart?.setOnClickListener {
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivity = null
    }
}