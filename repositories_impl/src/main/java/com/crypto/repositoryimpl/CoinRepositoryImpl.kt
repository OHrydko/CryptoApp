package com.crypto.repositoryimpl

import com.crypto.data_source.LocalCoinDataSource
import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val remoteCoinDataSource: RemoteCoinDataSource,
    private val localCoinDataSource: LocalCoinDataSource
) : CoinRepository {

    override suspend fun getCoins(): DataResult<List<Coin>> {
        return remoteCoinDataSource.getCoins()
    }

    override suspend fun getDetails(id: String): DataResult<CoinDetails> {
        return remoteCoinDataSource.getDetails(id)
    }

    override suspend fun insertCoins(coins: List<Coin>): DataResult<Unit> {
        return localCoinDataSource.insertCoins(coins)
    }
}