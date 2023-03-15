package com.crypto.repositoryimpl

import androidx.paging.PagingData
import com.crypto.data_source.LocalCoinDataSource
import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val remoteCoinDataSource: RemoteCoinDataSource,
    private val localCoinDataSource: LocalCoinDataSource
) : CoinRepository {

    override fun getCoins(): Flow<PagingData<Coin>> {
        return flow {
            emit(
                withContext(Dispatchers.IO) {
//                    localCoinDataSource.getPagedCoinsFromDB().first()
                    remoteCoinDataSource.getCoins().first()
                }
            )
        }
    }

    override suspend fun getDetails(id: String): DataResult<CoinDetails> {
        return remoteCoinDataSource.getDetails(id)
    }

    override fun getPagedCoinsFromDB(): Flow<PagingData<Coin>> {
        return localCoinDataSource.getPagedCoinsFromDB()
    }

    override suspend fun insertCoins(coins: List<Coin>): DataResult<Unit> {
        return localCoinDataSource.insertCoins(coins)
    }

    override suspend fun getCoinsFromDB(): DataResult<List<Coin>> {
        return localCoinDataSource.getCoinsFromDB()
    }
}