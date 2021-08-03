package com.example.workoutapp.rest.responsemodels

import com.google.gson.annotations.SerializedName

data class MotivationElementResponse(
        @SerializedName(value = "id") val id: Long,
        @SerializedName(value = "phrase") val phrase: String,
        @SerializedName(value = "imageUrl") val imageUrl: String
): ParentElementResponse()