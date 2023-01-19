package com.crypto.cryptoapp.feature_coin_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import com.crypto.usecases.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _coinDetail = MutableStateFlow(CoinDetails.emptyState)
    val coinDetail = _coinDetail.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        if (!args.isNullOrEmpty()) {
            getDetails()
        }
    }

    private fun getDetails() {
        viewModelScope.launch {

            _loading.value = true

            val result = withContext(Dispatchers.IO) {
                args?.let { getCoinDetailsUseCase(it) }
            }

            when (result) {
                is DataResult.Success -> {

                    if (result.data.description.en.isEmpty()) {
                        result.data.description.en = "${result.data.name} description"
                    }
                    _coinDetail.value = result.data

                }
                is DataResult.Failure -> {
                    Timber.d("Fail")
                }
                else -> {}
            }

            _loading.value = false
        }
    }

    companion object {
        private const val KEY = "coinId"
    }

}