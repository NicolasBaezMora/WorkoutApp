package com.example.workoutapp.models


data class TemporizedExerciseModel (
    val idExercise: Long,
    val trainTime: Long,
    val breakTime: Long
) { var idPlan: Long? = null }