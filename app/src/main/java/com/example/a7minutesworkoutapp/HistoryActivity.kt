package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkoutapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private var mActivity: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)
        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(mActivity?.tbToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }
        mActivity?.tbToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}