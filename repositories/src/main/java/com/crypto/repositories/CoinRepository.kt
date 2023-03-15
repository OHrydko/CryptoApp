package com.crypto.repositories

import androidx.paging.PagingData
import com.crypto.domain_models.Coin
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<PagingData<Coin>>

    suspend fun getDetails(id: String): DataResult<CoinDetails>

    suspend fun insertCoins(coins: List<Coin>): DataResult<Unit>

    suspend fun getCoinsFromDB(): DataResult<List<Coin>>

}