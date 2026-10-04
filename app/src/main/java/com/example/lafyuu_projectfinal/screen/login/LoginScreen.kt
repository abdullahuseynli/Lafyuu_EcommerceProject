package com.example.lafyuu_projectfinal.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.lafyuu_projectfinal.R
import com.example.lafyuu_projectfinal.screen.components.AuthTextField
import com.example.lafyuu_projectfinal.screen.components.PrimaryButton
import com.example.lafyuu_projectfinal.screen.components.SocialButton
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1
import com.example.lafyuu_projectfinal.ui.theme.RedFB


@Composable
fun LoginScreen(
    onSignInSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val hasError = state.error != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(80.dp))

        Image(
            painter = painterResource(com.example.lafyuu_projectfinal.R.drawable.ic_logo2),
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text("Welcome to Lafyuu", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Blue63)
        Spacer(Modifier.height(8.dp))
        Text("Sign in to continue", fontSize = 12.sp, color = GrayB1)

        Spacer(Modifier.height(28.dp))

        AuthTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            placeholder = "Your Email",
            icon = com.example.lafyuu_projectfinal.R.drawable.ic_message,
            keyboardType = KeyboardType.Email,
            isError = hasError
        )
        Spacer(Modifier.height(8.dp))
        AuthTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            placeholder = "Password",
            icon = com.example.lafyuu_projectfinal.R.drawable.ic_password,
            isPassword = true,
            isError = hasError
        )

        state.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(
                it,
                color = RedFB,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(Modifier.height(16.dp))
        PrimaryButton(text = "Sign In", onClick = { viewModel.onSignInClick(onSignInSuccess) })

        Spacer(Modifier.height(16.dp))
        Text("OR", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = GrayB1)
        Spacer(Modifier.height(16.dp))

        SocialButton(
            "Login with Google",
            com.example.lafyuu_projectfinal.R.drawable.ic_google,
            onClick = { })
        Spacer(Modifier.height(8.dp))
        SocialButton("Login with facebook", R.drawable.ic_facebook, onClick = { })

        Spacer(Modifier.height(16.dp))
        Text(
            "Forgot Password?",
            fontSize = 12.sp,
            color = BlueFF,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { }
        )
        Spacer(Modifier.height(8.dp))
        Row {
            Text("Don't have an account? ", fontSize = 12.sp, color = GrayB1)
            Text(
                "Register",
                fontSize = 12.sp,
                color = BlueFF,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
        Spacer(Modifier.height(24.dp))
    }
}