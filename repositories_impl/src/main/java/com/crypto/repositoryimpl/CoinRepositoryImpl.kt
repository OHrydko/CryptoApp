package com.crypto.repositoryimpl

import android.util.Log
import com.crypto.data_source.LocalCoinDataSource
import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val remoteCoinDataSource: RemoteCoinDataSource,
    private val localCoinDataSource: LocalCoinDataSource
) : CoinRepository {

    override suspend fun getCoins(): DataResult<List<Coin>> {
        val response = remoteCoinDataSource.getCoins()

        when (response) {
            is DataResult.Success -> {
                localCoinDataSource.clearCoins()
                localCoinDataSource.insertCoins(response.data)
            }
            is DataResult.Failure -> {
                Log.d(TAG, response.throwable.message.toString())
            }
        }
        return response
    }

    override suspend fun getDetails(id: String): DataResult<CoinDetails> {
        return remoteCoinDataSource.getDetails(id)
    }

    override suspend fun insertCoins(coins: List<Coin>): DataResult<Unit> {
        return localCoinDataSource.insertCoins(coins)
    }

    override fun getCoinsFromDB(): Flow<List<Coin>> {
        return localCoinDataSource.getCoinsFromDB()
    }

    companion object {
        const val TAG = "CoinRepository"
    }
}