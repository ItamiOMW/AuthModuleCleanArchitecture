package com.example.authmodulecleanarchitecture.di

import com.example.authmodulecleanarchitecture.data.repository.AuthRepositoryImpl
import com.example.authmodulecleanarchitecture.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

}