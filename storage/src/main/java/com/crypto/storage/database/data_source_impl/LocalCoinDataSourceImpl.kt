package com.crypto.storage.database.data_source_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.crypto.data_source.LocalCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.storage.database.dao.CoinDao
import com.crypto.storage.database.mapper.toDomain
import com.crypto.storage.database.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCoinDataSourceImpl @Inject constructor(private val coinDao: CoinDao) :
    LocalCoinDataSource {

    override suspend fun insertCoins(coins: List<Coin>): DataResult<Unit> {
        coinDao.insertCoins(coins.map { it.toEntity() })
        return DataResult.Success(Unit)
    }

    override suspend fun getCoinsFromDB(): DataResult<List<Coin>> {
        return DataResult.Success(coinDao.getCoins().map { it.toDomain() })
    }

    override fun getPagedCoinsFromDB(): Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = { coinDao.getPagedCoins() }
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }

}