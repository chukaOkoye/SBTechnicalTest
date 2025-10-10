package com.example.sbtechincaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sbtechincaltest.data.model.PhotosModel
import com.example.sbtechincaltest.data.repository.PhotosRepositoryImpl
import com.example.sbtechincaltest.domain.usecase.PhotosUseCase
import com.example.sbtechincaltest.presentation.model.PhotosUIModel
import com.example.sbtechincaltest.presentation.model.PhotosUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(val useCase: PhotosUseCase): ViewModel() {
    private val _photosState = MutableStateFlow<PhotosUIState>(PhotosUIState.Loading)
    val photosState = _photosState.asStateFlow()

    fun loadPhotos(){
        viewModelScope.launch {
            _photosState.value = PhotosUIState.Loading
            val result = useCase.invoke()

            _photosState.value = result.fold(
                onSuccess = { photos ->
                    val uiModel = photos.toPhotosUIModelList()

                    PhotosUIState.Success(uiModel)
                },
                onFailure = { error ->
                    PhotosUIState.Error(error.message ?: "Not available")
                }
            )
        }
    }

    fun PhotosModel.toPhotosUIModel() : PhotosUIModel{
        return PhotosUIModel(
            thumbnailUrl = thumbnailUrl,
            title = title
        )
    }


    fun List<PhotosModel>.toPhotosUIModelList(): List<PhotosUIModel>{
        return this.map { it.toPhotosUIModel()}
    }
}

