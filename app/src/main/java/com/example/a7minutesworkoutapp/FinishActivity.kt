package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.a7minutesworkoutapp.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private var mActivity: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setToolbar()
        mActivity?.btnFinish?.setOnClickListener {
            finish()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(mActivity?.tbToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mActivity?.tbToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}