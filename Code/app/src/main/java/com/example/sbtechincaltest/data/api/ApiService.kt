package com.example.sbtechincaltest.data.api

import com.example.sbtechincaltest.data.model.PhotosModel
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(): List<PhotosModel>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

}