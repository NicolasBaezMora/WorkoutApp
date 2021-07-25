package com.example.workoutapp.room.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        tableName = "temporizedExercise",
        foreignKeys = [
            ForeignKey(
                entity = ExercisePlan::class,
                parentColumns = ["id"],
                childColumns = ["idPlan"]
            )
        ]
)
data class TemporizedExercise (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "idExercise") @NonNull val idExercise: Long,
    @ColumnInfo(name = "trainTime") @NonNull val trainTime: Long,
    @ColumnInfo(name = "breakTime") @NonNull val breakTime: Long,
    @ColumnInfo(name = "idPlan") @NonNull val idPlan: Long
)