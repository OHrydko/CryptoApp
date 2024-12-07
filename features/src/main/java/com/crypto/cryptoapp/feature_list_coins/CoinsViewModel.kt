package com.crypto.cryptoapp.feature_list_coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.cryptoapp.R
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.resources.ResProvider
import com.crypto.usecases.GetCoinsFromDBUseCase
import com.crypto.usecases.GetListCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getListCoinsUseCase: GetListCoinsUseCase,
    private val getCoinsFromDBUseCase: GetCoinsFromDBUseCase,
    private val resProvider: ResProvider
) : ViewModel() {

    private val _listCoins = MutableStateFlow<List<Coin>>(listOf())
    val listCoins = _listCoins.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        observeCoinsList()
        getListCoin()
    }

    private fun getListCoin() {
        viewModelScope.launch {
            _loading.value = true

            val result = withContext(Dispatchers.IO) {
                getListCoinsUseCase()
            }

            when (result) {
                is DataResult.Success -> {
                    Timber.tag(TAG).d(resProvider.getStringRes(R.string.success))
                }

                is DataResult.Failure -> {
                    _error.emit(
                        result.throwable.message
                            ?: resProvider.getStringRes(R.string.something_went_wrong)
                    )
                }
            }

            _loading.value = false
        }
    }

    private fun observeCoinsList() {
        getCoinsFromDBUseCase()
            .flowOn(Dispatchers.IO)
            .onEach { result ->
                _listCoins.update { result.sortedBy { it.marketCapRank.toInt() } }
            }.launchIn(viewModelScope)
    }

    companion object {
        private const val TAG = "CoinViewModelTag"
    }
}