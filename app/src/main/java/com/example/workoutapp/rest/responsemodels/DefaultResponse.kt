package com.example.workoutapp.rest.responsemodels

import com.google.gson.annotations.SerializedName

data class DefaultResponse<T> (
    @SerializedName(value = "ok") val ok: Boolean,
    @SerializedName(value = "message") val message: String,
    @SerializedName(value = "body") val body: T
)