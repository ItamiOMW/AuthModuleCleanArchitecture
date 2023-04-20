package com.example.authmodulecleanarchitecture.presentation.state

data class RegisterState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val repeatedPasswordInput: String = "",
    val isPasswordShown: Boolean = false,
    val isRepeatedPasswordShown: Boolean = false,
    val isInputValid: Boolean = false,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyRegistered: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
