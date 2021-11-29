package com.crypto.usecases_impl

import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import com.crypto.repositories.CoinRepository
import com.crypto.usecases.GetCoinDetailsUseCase
import javax.inject.Inject

class GetCoinDetailsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : GetCoinDetailsUseCase {

    override suspend fun invoke(id: String): DataResult<CoinDetails> {
        return repository.getDetails(id)
    }
}