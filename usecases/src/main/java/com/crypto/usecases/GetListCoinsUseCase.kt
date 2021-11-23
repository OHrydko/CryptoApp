package com.crypto.usecases

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult

interface GetListCoinsUseCase {
    suspend operator fun invoke(): DataResult<List<Coin>>
}