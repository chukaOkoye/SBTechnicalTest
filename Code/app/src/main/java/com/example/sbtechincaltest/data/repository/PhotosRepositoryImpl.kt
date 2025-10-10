package com.example.sbtechincaltest.data.repository

import com.example.sbtechincaltest.data.api.ApiService
import com.example.sbtechincaltest.data.model.PhotosModel
import com.example.sbtechincaltest.domain.repository.PhotosRepository
import okio.IOException
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor (val apiService: ApiService): PhotosRepository {

    override suspend fun fetchPhotos(): Result<List<PhotosModel>>{
        return try {
            val response = apiService.getPhotos()
            if(response.isNullOrEmpty()){
                return Result.failure(Exception("Empty or Null"))
            }
            Result.success(response)
        } catch(e: IOException){
            Result.failure(e)
        }
    }
}