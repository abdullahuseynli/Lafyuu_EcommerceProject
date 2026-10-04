package com.example.lafyuu_projectfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lafyuu_projectfinal.navigation.NavGraph
import com.example.lafyuu_projectfinal.ui.theme.Lafyuu_ProjectFinalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lafyuu_ProjectFinalTheme {
                NavGraph()
            }
        }
    }
}



