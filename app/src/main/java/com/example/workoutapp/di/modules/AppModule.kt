package com.example.workoutapp.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workoutapp.rest.services.ApiRestService
import com.example.workoutapp.room.roomdatabase.WorkOutRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named(value = "httpClientInstance")
    fun provideHttpClientInstance(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    @Named(value = "retrofitInstance")
    fun provideRetrofitInstance(
        @Named(value = "httpClientInstance") httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.37:8080/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    @Named(value = "apiRestServiceInstance")
    fun provideApiRestServiceInstance(): Class<ApiRestService> = ApiRestService::class.java

    @Singleton
    @Provides
    @Named(value = "roomDatabaseInstance")
    fun provideRoomDatabaseInstance(
            @ApplicationContext context: Context
    ): WorkOutRoomDatabase = Room.databaseBuilder(
            context,
            WorkOutRoomDatabase::class.java,
            "workoutroomdatabase"
    ).build()


}