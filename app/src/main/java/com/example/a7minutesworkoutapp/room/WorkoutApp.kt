package com.example.a7minutesworkoutapp.room

import android.app.Application

class WorkoutApp : Application() {
    val database by lazy {
        HistoryDatabase.getInstance(this)
    }
}