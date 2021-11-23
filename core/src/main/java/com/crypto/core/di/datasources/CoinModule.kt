package com.crypto.core.di.datasources

import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.network.data_sources_impl.CoinDataSourcesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
abstract class CoinModule {

    @Binds
    abstract fun bindUserDataSource(
        userCoinDataSourceImpl: CoinDataSourcesImpl
    ): RemoteCoinDataSource

}