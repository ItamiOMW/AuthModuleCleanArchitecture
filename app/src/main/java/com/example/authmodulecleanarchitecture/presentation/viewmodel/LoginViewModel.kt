package com.example.authmodulecleanarchitecture.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authmodulecleanarchitecture.R
import com.example.authmodulecleanarchitecture.domain.model.LoginInputValidationType
import com.example.authmodulecleanarchitecture.domain.repository.AuthRepository
import com.example.authmodulecleanarchitecture.domain.use_case.ValidateLoginInputUseCase
import com.example.authmodulecleanarchitecture.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val application: Application,
): ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    fun onEmailInputChange(newValue: String) {
        loginState = loginState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onTogglePasswordVisualTransformation() {
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    fun onLogInClick() {
        loginState = loginState.copy(isLoading = true,)
        viewModelScope.launch {
            val loginResult = authRepository.login(
                loginState.emailInput, loginState.passwordInput
            )
            loginState = loginState.copy(
                isLoading = false,
                isSuccessfullyLoggedIn = loginResult,
                errorMessageLoginProcess = if (!loginResult) "Couldn't login" else null
            )
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            email = loginState.emailInput,
            password = loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }


    private fun processInputValidationType(type: LoginInputValidationType) {
        loginState = when (type) {
            LoginInputValidationType.EmptyField -> {
                loginState.copy(
                    errorMessage = application.getString(R.string.error_empty_field),
                    isInputValid = false
                )
            }

            LoginInputValidationType.NoEmail -> {
                loginState.copy(
                    errorMessage = application.getString(R.string.error_incorrect_email),
                    isInputValid = false
                )
            }

            LoginInputValidationType.Valid -> {
                loginState.copy(
                    errorMessage = null,
                    isInputValid = true
                )
            }
        }
    }

}