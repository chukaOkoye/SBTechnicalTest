package com.example.sbtechincaltest.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sbtechincaltest.presentation.screens.LoginScreen
import com.example.sbtechincaltest.presentation.screens.PhotosScreen
import com.example.sbtechincaltest.presentation.viewmodel.LoginViewModel

import com.example.sbtechincaltest.presentation.viewmodel.PhotosViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val isAuthenticated by loginViewModel.isAuthenticated.collectAsState()

    // Pick the start destination *once* based on current auth state
    val startDestination = if (isAuthenticated) "photos_screen" else "login_screen"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login_screen") {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                loginViewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate("photos_screen")
                }
            )
        }

        composable("photos_screen") {
            val viewModel: PhotosViewModel = hiltViewModel()
            PhotosScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.navigate("login_screen") {
                    popUpTo("photos_screen") { inclusive = true }
                } }
            )
        }
    }
}
