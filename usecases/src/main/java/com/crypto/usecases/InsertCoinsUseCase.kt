package com.crypto.usecases

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult

interface InsertCoinsUseCase {
    suspend operator fun invoke(coins: List<Coin>): DataResult<Unit>
}