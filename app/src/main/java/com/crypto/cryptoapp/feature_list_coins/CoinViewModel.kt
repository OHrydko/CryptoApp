package com.crypto.cryptoapp.feature_list_coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.usecases.GetCoinsFromDBUseCase
import com.crypto.usecases.GetListCoinsUseCase
import com.crypto.usecases.InsertCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val useCase: GetListCoinsUseCase,
    private val insertCoinsUseCase: InsertCoinsUseCase,
    private val getCoinsFromDBUseCase: GetCoinsFromDBUseCase,
) : ViewModel() {

    private val _listCoins = MutableSharedFlow<List<Coin>>()
    val listCoins = _listCoins.asSharedFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun getListCoin() {
        viewModelScope.launch {

            _loading.value = true

            val result = withContext(Dispatchers.IO) {
                useCase()
            }
            when (result) {
                is DataResult.Success -> {
                    _listCoins.emit(result.data)
                    saveToDB(result.data)
                }
                is DataResult.Failure -> {
                    Timber.d("Fail")
                }
            }

            _loading.value = false
        }
    }

    fun getListCoinFromDB() {
        viewModelScope.launch {

            _loading.value = true

            val result = withContext(Dispatchers.IO) {
                getCoinsFromDBUseCase()
            }
            when (result) {
                is DataResult.Success -> {
                    _listCoins.emit(result.data)
                }
                is DataResult.Failure -> {
                    Timber.d("Fail")
                }
            }

            _loading.value = false
        }
    }

    private fun saveToDB(coins: List<Coin>) {

        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                insertCoinsUseCase(coins = coins)
            }

            when (result) {
                is DataResult.Success -> {
                    Timber.d("insert coins")
                }
                is DataResult.Failure -> {
                }
            }
        }
    }
}