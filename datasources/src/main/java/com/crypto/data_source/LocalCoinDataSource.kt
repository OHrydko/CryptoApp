package com.crypto.data_source

import androidx.paging.PagingData
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import kotlinx.coroutines.flow.Flow

interface LocalCoinDataSource {

    suspend fun insertCoins(coins: List<Coin>): DataResult<Unit>

    suspend fun getCoinsFromDB(): DataResult<List<Coin>>

    fun getPagedCoinsFromDB(): Flow<PagingData<Coin>>
}