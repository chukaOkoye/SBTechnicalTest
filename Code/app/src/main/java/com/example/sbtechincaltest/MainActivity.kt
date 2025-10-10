package com.example.sbtechincaltest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sbtechincaltest.presentation.navigation.MainNavGraph
import com.example.sbtechincaltest.presentation.screens.LoginScreen
import com.example.sbtechincaltest.presentation.screens.PhotosScreen
import com.example.sbtechincaltest.presentation.theme.SBTechnicalTheme
import com.example.sbtechincaltest.presentation.viewmodel.LoginViewModel
import com.example.sbtechincaltest.presentation.viewmodel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewmodel: LoginViewModel by viewModels()
    private val photosViewModel: PhotosViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            SBTechnicalTheme {
                Scaffold(modifier = Modifier.background(Color.White),
                ) { paddingValues ->
                    MainNavGraph(loginViewmodel, photosViewModel, paddingValues)
                }
            }

        }

    }

}