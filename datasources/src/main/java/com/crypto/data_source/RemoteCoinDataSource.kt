package com.crypto.data_source

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult

interface RemoteCoinDataSource {
    suspend fun getCoins(): DataResult<List<Coin>>
}