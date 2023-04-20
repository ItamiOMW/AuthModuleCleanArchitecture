package com.example.authmodulecleanarchitecture.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authmodulecleanarchitecture.R
import com.example.authmodulecleanarchitecture.domain.model.RegisterInputValidationType
import com.example.authmodulecleanarchitecture.domain.repository.AuthRepository
import com.example.authmodulecleanarchitecture.domain.use_case.ValidateRegisterInputUseCase
import com.example.authmodulecleanarchitecture.presentation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val application: Application,
): ViewModel() {

    var registerState by mutableStateOf(RegisterState())
        private set


    fun onEmailInputChange(newValue: String) {
        registerState = registerState.copy(emailInput = newValue)
        checkInputValidation()
    }


    fun onPasswordInputChange(newValue: String) {
        registerState = registerState.copy(passwordInput = newValue)
        checkInputValidation()
    }


    fun onRepeatedPasswordInputChange(newValue: String) {
        registerState = registerState.copy(repeatedPasswordInput = newValue)
        checkInputValidation()
    }


    fun onToggleRepeatedPasswordVisualTransformation() {
        registerState = registerState.copy(
            isRepeatedPasswordShown = !registerState.isRepeatedPasswordShown
        )
    }


    fun onTogglePasswordVisualTransformation() {
        registerState = registerState.copy(
            isPasswordShown = !registerState.isPasswordShown
        )
    }


    fun onRegisterClick() {
        registerState = registerState.copy(isLoading = true,)
        viewModelScope.launch {
            val registerResult = authRepository.register(
                registerState.emailInput, registerState.passwordInput
            )
            registerState = registerState.copy(
                isLoading = false,
                isSuccessfullyRegistered = registerResult,
                errorMessageLoginProcess = if (!registerResult) "Couldn't login" else null
            )
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateRegisterInputUseCase(
            email = registerState.emailInput,
            password = registerState.passwordInput,
            repeatedPassword = registerState.repeatedPasswordInput
        )
        processInputValidationType(validationResult)
    }


    private fun processInputValidationType(type: RegisterInputValidationType) {
        registerState = when (type) {
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(
                    errorMessage = application.getString(R.string.error_empty_field),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.NoEmail -> {
                registerState.copy(
                    errorMessage = application.getString(R.string.error_incorrect_email),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordsDoNotMatch -> {
                registerState.copy(
                    errorMessage = application.getString(R.string.error_passwords_do_not_match),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordTooShort -> {
                registerState.copy(
                    errorMessage = application.getString(R.string.error_short_password),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.Valid -> {
                registerState.copy(
                    errorMessage = null,
                    isInputValid = true
                )
            }
        }
    }
}