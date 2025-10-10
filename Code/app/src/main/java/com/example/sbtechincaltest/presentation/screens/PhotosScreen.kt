package com.example.sbtechincaltest.presentation.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sbtechincaltest.presentation.model.PhotosUIState
import com.example.sbtechincaltest.presentation.viewmodel.PhotosViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel,
    paddingValues: PaddingValues, navigateBack: () -> Unit
) {
    val state by viewModel.photosState.collectAsState()
    var searchText by remember { mutableStateOf("") }


    LaunchedEffect(true) {
        viewModel.loadPhotos()
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Photos") },
                navigationIcon = {
                    IconButton(onClick = navigateBack)
                    {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        }) { paddingValues ->

        when (state) {
            is PhotosUIState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues = paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Spacer(Modifier.height(46.dp))
                    CircularProgressIndicator()
                }
            }

            is PhotosUIState.Error -> {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(paddingValues = paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(Modifier.height(46.dp))
                        Text(
                            text = "Error loading photos, please try again",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadPhotos() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            is PhotosUIState.Success -> {
                val success = state as PhotosUIState.Success
                val photos = success.photosList

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues = paddingValues)
                ) {
                    TextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Search...") }
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(items = photos.filter {
                            it.title.contains(searchText)
                        }) { photo ->
                            PhotosRowScreen(photo)
                        }

                    }
                }
            }
        }
    }

}




