package com.crypto.core.di.usecases

import com.crypto.usecases.GetListCoinsUseCase
import com.crypto.usecases_impl.GetListCoinsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindIsEnableBiometricSettingsUseCase(
        useCase: GetListCoinsUseCaseImpl
    ): GetListCoinsUseCase

}