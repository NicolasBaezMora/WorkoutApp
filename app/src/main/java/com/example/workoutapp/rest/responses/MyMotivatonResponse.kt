package com.example.workoutapp.rest.responses

import com.google.gson.annotations.SerializedName


data class MotivationsResponse(
        @SerializedName(value = "motivationList") val motivationList: List<MotivationElementResponse>
)

data class MotivationElementResponse(
        @SerializedName(value = "id") val id: Int,
        @SerializedName(value = "phrase") val phrase: String,
        @SerializedName(value = "imageUrl") val imageUrl: String
): ParentElementResponse()