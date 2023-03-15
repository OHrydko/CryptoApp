package com.crypto.usecases_impl

import androidx.paging.PagingData
import com.crypto.domain_models.Coin
import com.crypto.repositories.CoinRepository
import com.crypto.usecases.GetListCoinsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCoinsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : GetListCoinsUseCase {

    override fun invoke(): Flow<PagingData<Coin>> {
        return repository.getCoins()
    }

}