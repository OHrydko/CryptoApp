package com.crypto.cryptoapp.feature_list_coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.crypto.domain_models.Coin
import com.crypto.usecases.GetListCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinsUseCase: GetListCoinsUseCase,
) : ViewModel() {

    fun getCoins(): Flow<PagingData<Coin>> = getCoinsUseCase().cachedIn(viewModelScope)

}