package com.crypto.network.service

import com.crypto.network.model.response.CoinJson
import retrofit2.http.GET

interface CoinService {

    @GET("v3/coins/markets?vs_currency=usd")
    suspend fun getCoins(): GenericResponse<List<CoinJson>>
}