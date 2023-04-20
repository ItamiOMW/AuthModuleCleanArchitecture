package com.example.authmodulecleanarchitecture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authmodulecleanarchitecture.presentation.HomeScreen
import com.example.authmodulecleanarchitecture.presentation.LoginScreen
import com.example.authmodulecleanarchitecture.presentation.RegisterScreen


@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(Screen.RegisterScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                onRegisterSuccessNavigation = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
    }

}