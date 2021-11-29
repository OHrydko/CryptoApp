package com.crypto.cryptoapp.feature_coin_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.domain_models.DataResult
import com.crypto.usecases.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
) : ViewModel() {

    private val args = savedStateHandle.get<String>(KEY)

    init {
        if (!args.isNullOrEmpty()) {
            getDetails()
        }
    }

    private fun getDetails() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                args?.let { getCoinDetailsUseCase(it) }
            }

            when (result) {
                is DataResult.Success -> {
                    Timber.d("TEST SUCCESS")
                }
                is DataResult.Failure -> {
                    Timber.d("TEST Fail")
                }
            }
        }
    }

    companion object {
        private const val KEY = "coinId"
    }

}