package com.example.workoutapp.room.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "favoriteExercise"
)
data class FavoriteExercise (
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "valueIdExercise") @NonNull val valueIdExercise: Long
)