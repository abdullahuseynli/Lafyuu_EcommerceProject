package com.example.lafyuu_projectfinal.screen.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun onEmailChange(v: String) = _state.update { it.copy(email = v, error = null) }
    fun onPasswordChange(v: String) = _state.update { it.copy(password = v, error = null) }

    fun onSignInClick(onSuccess: () -> Unit) {
        val s = _state.value
        val error = when {
            !Patterns.EMAIL_ADDRESS.matcher(s.email).matches() -> "Oops! Your Email is Not Correct"
            s.password.length < 6 -> "Oops! Your Password is Not Correct"
            else -> null
        }
        if (error != null) _state.update { it.copy(error = error) } else onSuccess()
    }
}