package com.crypto.core.di.usecases

import com.crypto.usecases.GetCoinDetailsUseCase
import com.crypto.usecases.GetCoinsFromDBUseCase
import com.crypto.usecases.GetListCoinsUseCase
import com.crypto.usecases.InsertCoinsUseCase
import com.crypto.usecases_impl.GetCoinDetailsUseCaseImpl
import com.crypto.usecases_impl.GetCoinsFromDBUseCaseImpl
import com.crypto.usecases_impl.GetListCoinsUseCaseImpl
import com.crypto.usecases_impl.InsertCoinsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetListCoinsUseCase(
        useCase: GetListCoinsUseCaseImpl
    ): GetListCoinsUseCase

    @Binds
    fun bindGetCoinDetailsUseCase(
        useCase: GetCoinDetailsUseCaseImpl
    ): GetCoinDetailsUseCase

    @Binds
    fun bindInsertCoinsUseCase(
        useCase: InsertCoinsUseCaseImpl
    ): InsertCoinsUseCase

    @Binds
    fun bindGetLocalListCoinsUseCase(
        useCase: GetCoinsFromDBUseCaseImpl
    ): GetCoinsFromDBUseCase
}