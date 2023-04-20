package com.example.authmodulecleanarchitecture.domain.use_case

import com.example.authmodulecleanarchitecture.domain.model.LoginInputValidationType
import javax.inject.Inject

class ValidateLoginInputUseCase @Inject constructor() {

    operator fun invoke(email: String, password: String): LoginInputValidationType {

        if (email.isEmpty() && password.isEmpty()) {
            return LoginInputValidationType.EmptyField
        }

        if ("@" !in email) {
            return LoginInputValidationType.NoEmail
        }

        return LoginInputValidationType.Valid
    }

}