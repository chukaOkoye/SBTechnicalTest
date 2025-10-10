package com.example.sbtechincaltest.domain.repository

import com.example.sbtechincaltest.data.model.PhotosModel

interface PhotosRepository {

    suspend fun fetchPhotos(): Result<List<PhotosModel>>

}