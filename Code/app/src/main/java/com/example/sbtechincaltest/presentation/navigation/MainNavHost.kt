package com.example.sbtechincaltest.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sbtechincaltest.presentation.screens.LoginScreen
import com.example.sbtechincaltest.presentation.screens.PhotosScreen
import com.example.sbtechincaltest.presentation.viewmodel.LoginViewModel
import com.example.sbtechincaltest.presentation.viewmodel.PhotosViewModel

@Composable
fun MainNavGraph(loginViewModel: LoginViewModel, photosViewModel: PhotosViewModel, paddingValues: PaddingValues) {
    val isAuthenticated by loginViewModel.isAuthenticated.collectAsState()
    val navController = rememberNavController()

    LaunchedEffect(isAuthenticated) {
        if(isAuthenticated){
            navController.navigate("photos_screen"){
                popUpTo("login_screen"){ saveState = false
                    inclusive = true}
            }
        } else {
            navController.navigate("login_screen"){
                popUpTo("photos_screen"){
                    saveState = false
                    inclusive = false}
            }
        }
    }
    NavHost(navController = navController, startDestination = "login_screen") {
        composable(route = "login_screen") {
            LoginScreen(loginViewModel)
        }
        composable(route = "photos_screen") {
            PhotosScreen(photosViewModel, paddingValues) { navController.popBackStack() }
        }
    }
}