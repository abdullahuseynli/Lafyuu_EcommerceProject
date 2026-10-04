package com.example.lafyuu_projectfinal.screen.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.lafyuu_projectfinal.R
import com.example.lafyuu_projectfinal.ui.theme.BlueFF


@Composable
fun SplashScreen(
    onFinished: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isFinished by viewModel.isFinished.collectAsState()

    LaunchedEffect(isFinished) {
        if (isFinished) onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueFF),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(72.dp)
        )
    }
}