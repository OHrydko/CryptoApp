package com.crypto.data_source

import androidx.paging.PagingData
import com.crypto.domain_models.Coin
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import kotlinx.coroutines.flow.Flow

interface RemoteCoinDataSource {

    fun getCoins(): Flow<PagingData<Coin>>

    suspend fun getDetails(id: String): DataResult<CoinDetails>

}