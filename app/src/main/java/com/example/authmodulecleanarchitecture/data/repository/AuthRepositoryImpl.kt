package com.example.authmodulecleanarchitecture.data.repository

import com.example.authmodulecleanarchitecture.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

): AuthRepository {

    override suspend fun register(email: String, password: String): Boolean {
        delay(1000)
        return true
    }

    override suspend fun login(email: String, password: String): Boolean {
        delay(1000)
        return true
    }

}