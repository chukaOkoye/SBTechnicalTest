package com.example.sbtechincaltest.domain.usecase

import com.example.sbtechincaltest.data.model.PhotosModel
import com.example.sbtechincaltest.domain.repository.PhotosRepository
import javax.inject.Inject

class PhotosUseCase @Inject constructor(val repository: PhotosRepository){

    suspend operator fun invoke(): Result<List<PhotosModel>>{
        return repository.fetchPhotos()
    }

}

