package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var mActivity: ActivityExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(mActivity?.tbActivityExerciseToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mActivity?.tbActivityExerciseToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}