package com.crypto.usecases

import androidx.paging.PagingData
import com.crypto.domain_models.Coin
import kotlinx.coroutines.flow.Flow

interface GetListCoinsUseCase {
    operator fun invoke(): Flow<PagingData<Coin>>
}