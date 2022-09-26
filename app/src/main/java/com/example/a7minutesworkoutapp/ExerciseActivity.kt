package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.a7minutesworkoutapp.databinding.ActivityExerciseBinding
import com.google.android.material.snackbar.Snackbar

class ExerciseActivity : AppCompatActivity() {

    private var mActivity: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var index = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        exerciseList = Constant.getExercises()

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
        mActivity?.pbActivityExerciseProgressRest?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                mActivity?.pbActivityExerciseProgressRest?.progress = 10 - restProgress
                mActivity?.tvActivityExerciseTimerRest?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                index++
                setupExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar() {
        mActivity?.pbActivityExerciseProgress?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                mActivity?.pbActivityExerciseProgress?.progress = 30 - exerciseProgress
                mActivity?.tvActivityExerciseTimer?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (index < exerciseList!!.size - 1) {
                    setupRestView()
                } else {
                    Snackbar.make(
                        mActivity!!.root,
                        "Congratulations, you have completed the 7 minutes workout.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        }.start()
    }

    private fun setupRestView() {
        mActivity?.flActivityExerciseTimer?.visibility = View.INVISIBLE
        mActivity?.tvActivityExerciseName?.visibility = View.INVISIBLE
        mActivity?.ivActivityExerciseImage?.visibility = View.INVISIBLE
        mActivity?.tvActivityExerciseTitle?.visibility = View.VISIBLE
        mActivity?.flActivityExerciseTimerRest?.visibility = View.VISIBLE
        mActivity?.tvActivityExerciseUpcomingExercise?.visibility = View.VISIBLE
        mActivity?.tvActivityExerciseUpcomingExercise?.text = exerciseList!![index + 1].name
        mActivity?.TextView1?.visibility = View.VISIBLE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setupExerciseView() {
        mActivity?.tvActivityExerciseTitle?.visibility = View.INVISIBLE
        mActivity?.flActivityExerciseTimerRest?.visibility = View.INVISIBLE
        mActivity?.tvActivityExerciseUpcomingExercise?.visibility = View.INVISIBLE
        mActivity?.TextView1?.visibility = View.INVISIBLE
        mActivity?.flActivityExerciseTimer?.visibility = View.VISIBLE
        mActivity?.tvActivityExerciseName?.visibility = View.VISIBLE
        mActivity?.tvActivityExerciseName?.text = exerciseList!![index].name
        mActivity?.ivActivityExerciseImage?.visibility = View.VISIBLE
        mActivity?.ivActivityExerciseImage?.setImageResource(exerciseList!![index].image)

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        mActivity = null
    }

}