package com.example.sbtechincaltest.di

import com.example.sbtechincaltest.data.api.ApiService
import com.example.sbtechincaltest.data.api.ApiService.Companion.BASE_URL
import com.example.sbtechincaltest.data.repository.PhotosRepositoryImpl
import com.example.sbtechincaltest.domain.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // API Service
    @Provides
    @Singleton
    fun provideApiService(): ApiService {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun providePhotosRepository(apiService: ApiService): PhotosRepository =
        PhotosRepositoryImpl(apiService)
}