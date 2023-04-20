package com.example.authmodulecleanarchitecture.presentation.navigation

sealed class Screen(val route: String) {

    object LoginScreen: Screen(route = "login")

    object RegisterScreen: Screen(route = "register")

    object HomeScreen: Screen(route = "home")

}
