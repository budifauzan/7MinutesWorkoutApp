package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkoutapp.databinding.ActivityHistoryBinding
import com.example.a7minutesworkoutapp.room.HistoryDAO
import com.example.a7minutesworkoutapp.room.HistoryEntity
import com.example.a7minutesworkoutapp.room.WorkoutApp
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var mActivity: ActivityHistoryBinding? = null
    private var historyDAO: HistoryDAO? = null
    private var historyList = ArrayList<HistoryEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setToolbar()

        historyDAO = (application as WorkoutApp).database.historyDAO()
        getHistoryData(historyDAO!!)
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

    private fun getHistoryData(historyDAO: HistoryDAO) {
        lifecycleScope.launch {
            historyDAO.getHistoryData().collect { items ->
                if (items.isNotEmpty()) {
                    for (i in items) {
                        historyList.add(i)
                    }
                    mActivity?.textView8?.visibility = VISIBLE
                    mActivity?.rvHistoryList?.visibility = VISIBLE
                    mActivity?.tvNotice?.visibility = INVISIBLE
                    setRecyclerView()
                } else {
                    mActivity?.textView8?.visibility = INVISIBLE
                    mActivity?.rvHistoryList?.visibility = INVISIBLE
                    mActivity?.tvNotice?.visibility = VISIBLE
                }
            }
        }
    }

    private fun setRecyclerView() {
        mActivity?.rvHistoryList?.adapter = HistoryAdapter(historyList)
        mActivity?.rvHistoryList?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivity = null
    }
}