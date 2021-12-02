package com.crypto.storage.database.data_source_impl

import com.crypto.data_source.LocalCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.storage.database.dao.CoinDao
import com.crypto.storage.database.mapper.toEntity
import javax.inject.Inject

class LocalCoinDataSourceImpl @Inject constructor(private val coinDao: CoinDao) :
    LocalCoinDataSource {

    override suspend fun insertCoins(coins: List<Coin>): DataResult<Unit> {
        coinDao.insertCoins(coins.map { it.toEntity() })
        return DataResult.Success(Unit)
    }


}