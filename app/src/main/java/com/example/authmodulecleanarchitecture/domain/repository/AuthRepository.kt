package com.example.authmodulecleanarchitecture.domain.repository

interface AuthRepository {

    suspend fun register(email: String, password: String): Boolean

    suspend fun login(email: String, password: String): Boolean

}