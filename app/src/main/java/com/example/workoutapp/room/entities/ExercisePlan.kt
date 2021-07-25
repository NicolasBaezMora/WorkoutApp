package com.example.workoutapp.room.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "exercisePlan"
)
data class ExercisePlan (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "title") @NonNull val title: String
)