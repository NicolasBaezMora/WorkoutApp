package com.example.workoutapp.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.workoutapp.room.entities.TemporizedExercise

@Dao
interface TemporizedExerciseDao {

    @Query("INSERT INTO temporizedExercise (idExercise, trainTime, breakTime, idPlan) VALUES(:idExercise, :trainTime, :breakTime, :idPlan)")
    fun addTemporizedExercise(
            idExercise: Long,
            trainTime: Long,
            breakTime: Long,
            idPlan: Long
    )

    @Query("SELECT * FROM temporizedExercise WHERE idPlan = :idPlan")
    fun getTemporizedExercisesFromPlan(idPlan: Long): List<TemporizedExercise>

    @Query("DELETE FROM temporizedExercise WHERE idPlan = :idPlan")
    fun deleteExerciseByPlanId(idPlan: Long)

}