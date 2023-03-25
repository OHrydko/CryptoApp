package com.crypto.usecases_impl

import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import com.crypto.usecases.GetCoinsFromDBUseCase
import javax.inject.Inject

class GetCoinsFromDBUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : GetCoinsFromDBUseCase {

    override suspend fun invoke(): DataResult<List<Coin>> {
        return repository.getCoinsFromDB()
    }

}