package com.crypto.data_source

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import kotlinx.coroutines.flow.Flow

interface LocalCoinDataSource {

    suspend fun insertCoins(coins: List<Coin>): DataResult<Unit>

    fun getCoinsFromDB(): Flow<List<Coin>>

    suspend fun clearCoins()
}