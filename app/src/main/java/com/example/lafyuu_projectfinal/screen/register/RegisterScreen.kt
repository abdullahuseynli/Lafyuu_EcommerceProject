package com.example.lafyuu_projectfinal.screen.register

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
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1
import com.example.lafyuu_projectfinal.ui.theme.RedFB

@Composable
fun RegisterScreen(
    onSignUpSuccess: () -> Unit,
    onSignInClick: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

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
            painter = painterResource(R.drawable.ic_logo2),
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text("Let's Get Started", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Blue63)
        Spacer(Modifier.height(8.dp))
        Text("Create an new account", fontSize = 12.sp, color = GrayB1)

        Spacer(Modifier.height(28.dp))

        AuthTextField(
            value = state.fullName,
            onValueChange = viewModel::onFullNameChange,
            placeholder = "Full Name",
            icon = R.drawable.ic_person
        )
        Spacer(Modifier.height(8.dp))

        AuthTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            placeholder = "Your Email",
            icon = R.drawable.ic_message,
            keyboardType = KeyboardType.Email
        )
        Spacer(Modifier.height(8.dp))

        AuthTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            placeholder = "Password",
            icon = R.drawable.ic_password,
            isPassword = true
        )
        Spacer(Modifier.height(8.dp))

        AuthTextField(
            value = state.passwordAgain,
            onValueChange = viewModel::onPasswordAgainChange,
            placeholder = "Password Again",
            icon = R.drawable.ic_password,
            isPassword = true
        )

        state.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = RedFB, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))
        PrimaryButton(text = "Sign Up", onClick = { viewModel.onSignUpClick(onSignUpSuccess) })

        Spacer(Modifier.height(24.dp))
        Row {
            Text("Have a account? ", fontSize = 12.sp, color = GrayB1)
            Text(
                "Sign In",
                fontSize = 12.sp,
                color = BlueFF,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onSignInClick() }
            )
        }
        Spacer(Modifier.height(24.dp))
    }
}