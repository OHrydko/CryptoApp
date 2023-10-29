package com.crypto.cryptoapp.feature_coin_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.cryptoapp.R
import com.crypto.domain_models.CoinDetails
import com.crypto.domain_models.DataResult
import com.crypto.resources.ResProvider
import com.crypto.usecases.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val resProvider: ResProvider
) : ViewModel() {

    private val args = savedStateHandle.get<String>(KEY)

    private val _coinDetail = MutableStateFlow(CoinDetails.emptyState)
    val coinDetail = _coinDetail.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

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
                    _coinDetail.value = result.data
                }

                is DataResult.Failure -> {
                    _error.emit(
                        result.throwable.message
                            ?: resProvider.getStringRes(R.string.something_went_wrong)
                    )
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