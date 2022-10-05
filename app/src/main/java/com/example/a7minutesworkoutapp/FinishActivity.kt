package com.example.a7minutesworkoutapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkoutapp.databinding.ActivityFinishBinding
import com.example.a7minutesworkoutapp.room.HistoryDAO
import com.example.a7minutesworkoutapp.room.HistoryEntity
import com.example.a7minutesworkoutapp.room.WorkoutApp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var mActivity: ActivityFinishBinding? = null
    private var historyDAO: HistoryDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setToolbar()
        mActivity?.btnFinish?.setOnClickListener {
            finish()
        }
        historyDAO = (application as WorkoutApp).database.historyDAO()
        addHistorytoDatabase(historyDAO!!)
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

    private fun addHistorytoDatabase(historyDAO: HistoryDAO) {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        val simpleDateFormat = SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.getDefault())
        val date = simpleDateFormat.format(dateTime)

        lifecycleScope.launch {
            historyDAO.insert(HistoryEntity(date))
        }

    }

}