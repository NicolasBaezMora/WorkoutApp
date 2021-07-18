package com.example.workoutapp.di.modules

import com.example.workoutapp.rest.services.ApiRestService
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

}