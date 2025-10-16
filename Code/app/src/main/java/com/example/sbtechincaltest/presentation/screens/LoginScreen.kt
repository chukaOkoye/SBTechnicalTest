package com.example.sbtechincaltest.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sbtechincaltest.presentation.viewmodel.LoginViewModel
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, onLoginSuccess: () -> Unit) {


    val viewmodel by loginViewModel.user.collectAsState()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(viewmodel.isAuthenticated) {
        if (viewmodel.isAuthenticated == true) {
            onLoginSuccess()
        }
    }


    val focusManager = LocalFocusManager.current
    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Login") },
            )
        }) { paddingValues ->

        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){

            val context = LocalContext.current

            Spacer(Modifier.height(25.dp))

            Text(
                "Welcome back",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Left,
            )

            Spacer(Modifier.height(6.dp))

            Text("Login to your Student Beans Account",
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Left,
                fontSize = 12.sp,

                )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = viewmodel.email,
                singleLine = true,
                onValueChange = {
                    loginViewModel.updateEmail(it)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down)}
                ),
                label = {
                    Text("Enter your email address")
                },

                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).fillMaxWidth(),
                placeholder = {
                    Text(text = "Email")
                },
                supportingText = {
                    viewmodel.emailError?.let { Text(it)}
                },
                isError = viewmodel.emailError != null
            )
            OutlinedTextField(
                value = viewmodel.password,
                onValueChange = {
                    loginViewModel.updatePassword(it)
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = "Password")
                },
                label = {
                    Text("Enter your password")
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                supportingText = {
                    viewmodel.passwordError?.let{
                        Text(it)
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusRequester.requestFocus()}
                ),

                isError = viewmodel.passwordError != null,
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                },
                singleLine = true
            )

            Spacer(Modifier.height(25.dp))
            Button(
                content = { Text("Log in") },
                onClick = {

                        loginViewModel.login()

                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).focusRequester(focusRequester)
            )

        }

    }





}