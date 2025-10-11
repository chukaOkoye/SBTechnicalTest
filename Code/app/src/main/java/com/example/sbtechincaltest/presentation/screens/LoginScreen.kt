package com.example.sbtechincaltest.presentation.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.presentation.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel,) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}

    var emailError by remember {mutableStateOf(false)}
    var passwordError by remember {mutableStateOf(false)}

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(true) {
        loginViewModel.logout()
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
                value = email,
                singleLine = true,
                onValueChange = {
                    email = it
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
                    if(emailError){
                        Text("Enter a valid email address")
                    }
                },
                isError = emailError
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
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
                    if(passwordError){
                        Text("Enter a valid password")
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusRequester.requestFocus()}
                ),

                isError = passwordError,
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
                    emailError = email.isEmpty()
                    passwordError = password.isEmpty()

                    if(email.isNotEmpty() && password.isNotEmpty() ){
                        loginViewModel.login()
                    }

                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).focusRequester(focusRequester)
            )

        }

    }





}