package com.example.workoutapp.rest.services

import com.example.workoutapp.rest.responsemodels.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRestService {

    @GET(value = "exercise")
    fun getExercises(): Call<DefaultResponse<List<ExerciseElementResponse>>>

    @GET(value = "exercise/byCategory")
    fun getExercisesByCategory(@Query(value = "idCategory") idCategory: Long): Call<DefaultResponse<List<ExerciseElementResponse>>>


    @GET(value = "exercise/byTitle")
    fun getExercisesByFragTitle(@Query(value = "fragTitleExercise") fragTitleExercise: String): Call<DefaultResponse<List<ExerciseElementResponse>>>


    @GET(value = "category")
    fun getCategories(): Call<DefaultResponse<List<CategoryElementResponse>>>
    
    @GET(value = "motivation")
    fun getMotivations(): Call<DefaultResponse<List<MotivationElementResponse>>>

    @GET(value = "exercise/{id}")
    fun getExerciseById(@Path(value = "id") idExercise: Long): Call<DefaultResponse<ExerciseElementResponse>>

}












