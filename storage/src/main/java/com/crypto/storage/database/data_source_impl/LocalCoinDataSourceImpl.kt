package com.crypto.storage.database.data_source_impl

import com.crypto.data_source.LocalCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.storage.database.dao.CoinDao
import com.crypto.storage.database.mapper.toDomain
import com.crypto.storage.database.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LocalCoinDataSourceImpl @Inject constructor(private val coinDao: CoinDao) :
    LocalCoinDataSource {

    override suspend fun insertCoins(coins: List<Coin>): DataResult<Unit> {
        coinDao.insertCoins(coins.map { it.toEntity() })
        return DataResult.Success(Unit)
    }

    override fun getCoinsFromDB(): Flow<List<Coin>> {
        return flow {
            coinDao.getCoins().collect { coins -> emit(coins.map { coin -> coin.toDomain() }) }
        }
    }

    override suspend fun clearCoins() {
        coinDao.clearCoins()
    }

}