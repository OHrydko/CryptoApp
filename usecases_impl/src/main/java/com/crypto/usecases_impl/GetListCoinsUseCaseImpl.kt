package com.crypto.usecases_impl

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import com.crypto.usecases.GetListCoinsUseCase
import javax.inject.Inject

class GetListCoinsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : GetListCoinsUseCase {

    override suspend fun invoke(): DataResult<List<Coin>> {
        return repository.getCoins()
    }

}