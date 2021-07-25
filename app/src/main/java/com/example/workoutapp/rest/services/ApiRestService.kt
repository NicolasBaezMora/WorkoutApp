package com.example.workoutapp.rest.services

import com.example.workoutapp.rest.responses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRestService {

    @GET(value = "exercise")
    fun getExercises(): Call<List<ExerciseElementResponse>>

    @GET(value = "category")
    fun getCategories(): Call<List<CategoryElementResponse>>
    
    @GET(value = "motivation")
    fun getMotivations(): Call<List<MotivationElementResponse>>

    @GET(value = "exercise/{id}")
    fun getExerciseById(@Path(value = "id") idExercise: Long): Call<ExerciseElementResponse>

}