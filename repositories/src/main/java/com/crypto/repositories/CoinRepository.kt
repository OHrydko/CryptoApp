package com.crypto.repositories

import com.crypto.domain_models.Coin
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): DataResult<List<Coin>>

    suspend fun getDetails(id: String): DataResult<CoinDetails>

    suspend fun insertCoins(coins: List<Coin>): DataResult<Unit>

    fun getCoinsFromDB(): Flow<List<Coin>>

}