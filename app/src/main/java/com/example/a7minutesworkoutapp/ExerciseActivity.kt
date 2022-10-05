package com.example.a7minutesworkoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import com.example.a7minutesworkoutapp.databinding.ActivityExerciseBinding
import com.example.a7minutesworkoutapp.databinding.DialogCustomBackConfirmationBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var mActivity: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restTimerDuration: Long = 1
    private var restProgress = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseTimerDuration: Long = 1
    private var exerciseProgress = 0
    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var index = -1
    private var exerciseAdapter: ExerciseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        exerciseList = Constant.getExercises()
        tts = TextToSpeech(this, this)
        exerciseAdapter = ExerciseAdapter(exerciseList!!)

        setToolbar()
        setupRestView()
        setRecyclerView()
    }

    private fun setToolbar() {
        setSupportActionBar(mActivity?.tbActivityExerciseToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mActivity?.tbActivityExerciseToolbar?.setNavigationOnClickListener {
            backConfirmation()
        }
    }

    private fun backConfirmation() {
        val customDialog = Dialog(this)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        // Set dialog width to match parent
        val window = customDialog.window
        window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setRestProgressBar() {
        mActivity?.pbActivityExerciseProgressRest?.progress = restProgress

        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                mActivity?.pbActivityExerciseProgressRest?.progress = 10 - restProgress
                mActivity?.tvActivityExerciseTimerRest?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                index++
                exerciseList!![index].isSelected = true
                exerciseAdapter?.notifyDataSetChanged()
                setupExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar() {
        mActivity?.pbActivityExerciseProgress?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                mActivity?.pbActivityExerciseProgress?.progress = 30 - exerciseProgress
                mActivity?.tvActivityExerciseTimer?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (index < exerciseList!!.size - 1) {
                    exerciseList!![index].isSelected = false
                    exerciseList!![index].isCompleted = true
                    exerciseAdapter?.notifyDataSetChanged()
                    setupRestView()
                } else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
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

        try {
            val soundURI =
                Uri.parse("android.resource://com.example.a7minutesworkoutapp/" + R.raw.press_start)
            player = MediaPlayer.create(this, soundURI)
            player?.isLooping = false
            player?.start()
        } catch (e: Exception) {
            e.printStackTrace()
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
        speakOut(exerciseList!![index].name)
        setExerciseProgressBar()
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
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
        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
        }
        if (player != null) {
            player!!.stop()
        }
        mActivity = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The language specified is not supported")
            }
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    private fun setRecyclerView() {
        mActivity?.rvExerciseStatus?.adapter = exerciseAdapter
        mActivity?.rvExerciseStatus?.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
    }

    override fun onBackPressed() {
        backConfirmation()
    }

}