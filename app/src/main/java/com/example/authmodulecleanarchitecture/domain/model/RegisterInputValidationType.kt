package com.example.authmodulecleanarchitecture.domain.model

enum class RegisterInputValidationType {
    EmptyField,
    NoEmail,
    PasswordsDoNotMatch,
    PasswordTooShort,
    Valid
}