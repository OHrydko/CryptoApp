package com.crypto.data_source

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult

interface LocalCoinDataSource {

    suspend fun insertCoins(coins: List<Coin>): DataResult<Unit>

    suspend fun getCoinsFromDB(): DataResult<List<Coin>>

    suspend fun clearCoins()
}