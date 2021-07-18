package com.example.workoutapp.models

import java.io.Serializable

data class Motivation (
    val id: Int,
    val phrase: String,
    val imageUrl: String
): Serializable