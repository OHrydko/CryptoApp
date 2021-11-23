package com.crypto.repositoryimpl

import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val remoteCoinDataSource: RemoteCoinDataSource
) : CoinRepository {

    override suspend fun getCoins(): DataResult<List<Coin>> {
        return remoteCoinDataSource.getCoins()
    }
}