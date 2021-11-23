package com.crypto.repositories

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult

interface CoinRepository {

    suspend fun getCoins(): DataResult<List<Coin>>

}