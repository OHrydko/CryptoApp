package com.crypto.cryptoapp.feature_list_coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.domain_models.Coin
import com.crypto.domain_models.DataResult
import com.crypto.usecases.GetListCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val useCase: GetListCoinsUseCase
) : ViewModel() {

    private val _listCoins = MutableStateFlow(
        listOf(
            Coin(
                id = "",
                name = "Bitcoin",
                symbol = "BTC",
                image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
                currentPrice = "123",
                marketCap = "123",
                marketCapRank = "1"
            )
        )
    )
    val listCoins = _listCoins.asStateFlow()

    init {
        getListCoin()
    }

    private fun getListCoin() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase()
            }
            when (result) {
                is DataResult.Success -> {
                    _listCoins.value = result.data
                    Timber.d("TEST ${result.data[0].image}")
                }
                is DataResult.Failure -> {
                    Timber.d("Fail")
                }
            }
        }
    }
}