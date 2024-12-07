package com.crypto.app.di.datasources

import com.crypto.data_source.LocalCoinDataSource
import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.network.data_sources_impl.CoinDataSourcesImpl
import com.crypto.storage.database.data_source_impl.LocalCoinDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
abstract class CoinModule {

    @Binds
    abstract fun bindRemoteDataSource(
        userCoinDataSourceImpl: CoinDataSourcesImpl
    ): RemoteCoinDataSource

    @Binds
    abstract fun bindLocalCoinDataSource(
        userCoinDataSourceImpl: LocalCoinDataSourceImpl
    ): LocalCoinDataSource

}