package com.example.workoutapp.room.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workoutapp.room.dao.ExercisePlanDao
import com.example.workoutapp.room.dao.FavoriteExerciseDao
import com.example.workoutapp.room.dao.TemporizedExerciseDao
import com.example.workoutapp.room.entities.ExercisePlan
import com.example.workoutapp.room.entities.FavoriteExercise
import com.example.workoutapp.room.entities.TemporizedExercise

@Database(
        version = 1,
        entities = [
            ExercisePlan::class,
            FavoriteExercise::class,
            TemporizedExercise::class
        ]
)
abstract class WorkOutRoomDatabase: RoomDatabase() {

    abstract fun exercisePlanDao(): ExercisePlanDao
    abstract fun favoriteExerciseDao(): FavoriteExerciseDao
    abstract fun temporizedExerciseDao(): TemporizedExerciseDao

}