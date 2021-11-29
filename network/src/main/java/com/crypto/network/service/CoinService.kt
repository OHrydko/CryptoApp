package com.crypto.network.service

import com.crypto.network.model.response.CoinDetailJson
import com.crypto.network.model.response.CoinJson
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET("v3/coins/markets?vs_currency=usd")
    suspend fun getCoins(): GenericResponse<List<CoinJson>>

    @GET("v3/coins/{id}")
    suspend fun getCoinDetails(@Path("id") coinId: String): GenericResponse<CoinDetailJson>

}