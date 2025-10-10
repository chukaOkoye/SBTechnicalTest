package com.example.sbtechincaltest.presentation.model

sealed class PhotosUIState{
    data object Loading: PhotosUIState()

    data class Success(
        val photosList: List<PhotosUIModel>
    ): PhotosUIState()

    data class Error(
        val message: String
    ): PhotosUIState()
}
