package com.example.workoutapp.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.workoutapp.room.entities.FavoriteExercise

@Dao
interface FavoriteExerciseDao {


    @Query("INSERT INTO favoriteExercise (valueIdExercise) VALUES(:idExercise)")
    fun addExercise(idExercise: Long)

    @Query("SELECT * FROM favoriteExercise")
    fun getFavoritesExercises(): List<FavoriteExercise>

    @Query("SELECT * FROM favoriteExercise WHERE valueIdExercise = :idExercise")
    fun getFavoriteExerciseById(idExercise: Long): List<FavoriteExercise>

    @Query("DELETE FROM favoriteExercise WHERE valueIdExercise = :idExercise")
    fun deleteFavoriteExercise(idExercise: Long)


}