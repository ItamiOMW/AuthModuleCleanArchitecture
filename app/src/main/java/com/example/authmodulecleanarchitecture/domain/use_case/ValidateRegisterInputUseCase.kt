package com.example.authmodulecleanarchitecture.domain.use_case

import com.example.authmodulecleanarchitecture.domain.model.RegisterInputValidationType
import javax.inject.Inject

class ValidateRegisterInputUseCase @Inject constructor() {

    operator fun invoke(
        email: String,
        password: String,
        repeatedPassword: String,
    ): RegisterInputValidationType {

        if (email.isEmpty() && password.isEmpty() && repeatedPassword.isEmpty()) {
            return RegisterInputValidationType.EmptyField
        }

        if ("@" !in email) {
            return RegisterInputValidationType.NoEmail
        }

        if (password != repeatedPassword) {
            return RegisterInputValidationType.PasswordsDoNotMatch
        }

        if (password.count() < 8) {
            return RegisterInputValidationType.PasswordTooShort
        }

        return RegisterInputValidationType.Valid

    }

}