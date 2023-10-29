package com.crypto.usecases

import com.crypto.domain_models.Coin
import kotlinx.coroutines.flow.Flow

interface GetCoinsFromDBUseCase {
    operator fun invoke(): Flow<List<Coin>>
}