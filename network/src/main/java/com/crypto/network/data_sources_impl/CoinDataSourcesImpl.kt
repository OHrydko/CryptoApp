package com.crypto.network.data_sources_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import com.crypto.network.mapper.toDomain
import com.crypto.network.model.NetworkResponse
import com.crypto.network.paging_source.CoinsPagingSource
import com.crypto.network.service.CoinService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinDataSourcesImpl @Inject constructor(
    private val coinService: CoinService
) : RemoteCoinDataSource {
    override fun getCoins(): Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                CoinsPagingSource(coinService)
            }
        ).flow
    }

    override suspend fun getDetails(id: String): DataResult<CoinDetails> {
        return try {
            coinService.getCoinDetails(id).let {
                when (it) {
                    is NetworkResponse.ApiError -> {
                        DataResult.Failure(java.lang.RuntimeException())
                    }
                    is NetworkResponse.NetworkError -> {
                        DataResult.Failure(RuntimeException(it.error.localizedMessage))
                    }
                    is NetworkResponse.Success -> {
                        DataResult.Success(it.body.toDomain())
                    }
                    is NetworkResponse.UnknownError -> {
                        DataResult.Failure(RuntimeException(it.error.localizedMessage))
                    }
                }
            }
        } catch (e: Throwable) {
            DataResult.Failure(RuntimeException(e))
        }
    }


}