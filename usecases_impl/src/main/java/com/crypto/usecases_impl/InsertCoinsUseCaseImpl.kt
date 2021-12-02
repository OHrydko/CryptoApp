package com.crypto.usecases_impl

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import com.crypto.usecases.InsertCoinsUseCase
import javax.inject.Inject

class InsertCoinsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : InsertCoinsUseCase {

    override suspend fun invoke(coins: List<Coin>): DataResult<Unit> {
        return repository.insertCoins(coins)
    }

}