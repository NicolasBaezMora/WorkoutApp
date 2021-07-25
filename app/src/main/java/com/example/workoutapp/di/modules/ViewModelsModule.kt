package com.example.workoutapp.di.modules

import com.example.workoutapp.rest.services.ApiRestService
import com.example.workoutapp.room.dao.ExercisePlanDao
import com.example.workoutapp.room.dao.FavoriteExerciseDao
import com.example.workoutapp.room.dao.TemporizedExerciseDao
import com.example.workoutapp.room.roomdatabase.WorkOutRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import kotlin.reflect.typeOf

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelsModule {

    @ActivityRetainedScoped
    @Provides
    @Named(value = "retrofitServiceInstance")
    fun provideRetrofitServiceInstance(
        @Named(value = "retrofitInstance") retrofitInstance: Retrofit,
        @Named(value = "apiRestServiceInstance") restServiceClassInstance: Class<ApiRestService>
    ): ApiRestService = retrofitInstance.create(restServiceClassInstance)

    @ActivityRetainedScoped
    @Provides
    @Named(value = "exercisePlanDaoInstance")
    fun provideExercisePlanDaoInstance(
            @Named("roomDatabaseInstance") dbInstance: WorkOutRoomDatabase
    ): ExercisePlanDao = dbInstance.exercisePlanDao()


    @ActivityRetainedScoped
    @Provides
    @Named(value = "favoriteExerciseDaoInstance")
    fun provideFavoriteExerciseDaoInstance(
            @Named("roomDatabaseInstance") dbInstance: WorkOutRoomDatabase
    ): FavoriteExerciseDao = dbInstance.favoriteExerciseDao()


    @ActivityRetainedScoped
    @Provides
    @Named(value = "temporizedExerciseDaoInstance")
    fun provideTemporizedExerciseDaoInstance(
            @Named("roomDatabaseInstance") dbInstance: WorkOutRoomDatabase
    ): TemporizedExerciseDao = dbInstance.temporizedExerciseDao()

}