package com.crypto.core.di.repository

import com.crypto.repositories.CoinRepository
import com.crypto.repositoryimpl.CoinRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSignUpRepository(
        repositoryImpl: CoinRepositoryImpl
    ): CoinRepository

}