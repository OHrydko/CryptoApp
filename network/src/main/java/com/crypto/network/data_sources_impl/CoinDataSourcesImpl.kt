package com.crypto.network.data_sources_impl

import com.crypto.data_source.RemoteCoinDataSource
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.network.mapper.toDomain
import com.crypto.network.model.NetworkResponse
import com.crypto.network.service.CoinService
import javax.inject.Inject

class CoinDataSourcesImpl @Inject constructor(
    private val coinService: CoinService
) : RemoteCoinDataSource {
    override suspend fun getCoins(): DataResult<List<Coin>> {
        return try {
            coinService.getCoins().let {
                when (it) {
                    is NetworkResponse.ApiError -> {
                        DataResult.Failure(java.lang.RuntimeException())
                    }
                    is NetworkResponse.NetworkError -> {
                        DataResult.Failure(RuntimeException(it.error.localizedMessage))
                    }
                    is NetworkResponse.Success -> {
                        DataResult.Success(it.body.map { json ->
                            json.toDomain()
                        })
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