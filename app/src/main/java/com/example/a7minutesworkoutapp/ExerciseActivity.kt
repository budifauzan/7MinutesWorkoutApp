package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.a7minutesworkoutapp.databinding.ActivityExerciseBinding
import com.google.android.material.snackbar.Snackbar

class ExerciseActivity : AppCompatActivity() {

    private var mActivity: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setToolbar()
        setupRestView()
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

    private fun setRestProgressBar() {
        mActivity?.pbActivityExerciseProgress?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                mActivity?.pbActivityExerciseProgress?.progress = 10 - restProgress
                mActivity?.tvActivityExerciseTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                Snackbar.make(
                    mActivity!!.root,
                    "Here we are going to start our exercise",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }.start()
    }

    private fun setupRestView() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        mActivity = null
    }

}