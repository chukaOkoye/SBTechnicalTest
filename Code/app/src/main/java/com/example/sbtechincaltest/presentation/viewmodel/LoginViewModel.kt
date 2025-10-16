package com.example.sbtechincaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class User(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isAuthenticated: Boolean? = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {


    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    fun updateEmail(input: String){
        _user.update {
            it.copy(email = input)
        }
    }

    fun updatePassword(input: String){
        _user.update {
            it.copy(password = input)
        }
    }


    fun login(){
        viewModelScope.launch{

            val current = _user.value
            val emailError = if(current.email.isEmpty()) "Email required" else null
            val passwordError = if(current.password.isEmpty()) "Password required" else null

           _user.update {
               it.copy(
                   emailError = emailError,
                   passwordError = passwordError,
                   isAuthenticated = emailError == null && passwordError == null

               )
           }
        }


    }

    fun logout() {
        _user.update{
            it.copy(isAuthenticated = false)
        }
    }

}


