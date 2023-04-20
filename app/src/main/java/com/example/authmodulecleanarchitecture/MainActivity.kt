package com.example.authmodulecleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.authmodulecleanarchitecture.presentation.navigation.Navigation
import com.example.authmodulecleanarchitecture.presentation.ui.theme.AuthModuleCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthModuleCleanArchitectureTheme {
                Navigation()
            }
        }
    }
}