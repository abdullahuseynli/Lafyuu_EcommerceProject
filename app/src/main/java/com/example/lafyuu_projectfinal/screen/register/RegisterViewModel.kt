package com.example.lafyuu_projectfinal.screen.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class RegisterUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val passwordAgain: String = "",
    val error: String? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state

    fun onFullNameChange(v: String) = _state.update { it.copy(fullName = v, error = null) }
    fun onEmailChange(v: String) = _state.update { it.copy(email = v, error = null) }
    fun onPasswordChange(v: String) = _state.update { it.copy(password = v, error = null) }
    fun onPasswordAgainChange(v: String) =
        _state.update { it.copy(passwordAgain = v, error = null) }

    fun onSignUpClick(onSuccess: () -> Unit) {
        val s = _state.value
        val error = when {
            s.fullName.isBlank() -> "Full name is required"
            !Patterns.EMAIL_ADDRESS.matcher(s.email).matches() -> "Email is not valid"
            s.password.length < 6 -> "Password must be at least 6 characters"
            s.password != s.passwordAgain -> "Passwords do not match"
            else -> null
        }
        if (error != null) {
            _state.update { it.copy(error = error) }
        } else {
            onSuccess()
        }
    }
}