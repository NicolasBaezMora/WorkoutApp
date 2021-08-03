package com.example.workoutapp.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.workoutapp.room.entities.ExercisePlan

@Dao
interface ExercisePlanDao {

    @Query("INSERT INTO exercisePlan (title) VALUES(:titlePlan)")
    fun addPlan(titlePlan: String): Long

    @Query("SELECT * FROM exercisePlan")
    fun getPlans(): List<ExercisePlan>

    @Query("DELETE FROM exercisePlan WHERE id = :idPlan")
    fun deletePlanById(idPlan: Long)

}