package com.crypto.network.model.response

import com.google.gson.annotations.SerializedName

data class CoinJson(
    @SerializedName("id")
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("market_cap")
    val marketCap: String,
    @SerializedName("current_price")
    val currentPrice: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: String,
)