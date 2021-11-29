package com.crypto.usecases

import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult

interface GetCoinDetailsUseCase {
    suspend operator fun invoke(id: String): DataResult<CoinDetails>
}