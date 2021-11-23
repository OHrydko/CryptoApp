package com.crypto.core.di.network

import com.crypto.network.service.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    fun provideHomeService(retrofit: Retrofit): CoinService =
        retrofit.create(CoinService::class.java)

}

