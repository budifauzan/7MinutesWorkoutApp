package com.example.a7minutesworkoutapp

data class ExerciseModel(
    val id: Int,
    val name: String,
    val image: Int,
    var isCompleted: Boolean = false,
    var isSelected: Boolean = false
)


