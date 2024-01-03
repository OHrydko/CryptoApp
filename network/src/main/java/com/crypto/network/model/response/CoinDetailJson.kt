package com.crypto.network.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoinDetailJson(
    @SerializedName("id")
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: DescriptionJson,
    @SerializedName("image")
    val image: CoinImageJson,
    @SerializedName("market_data")
    val marketData: MarketDataJson
)

@Keep
data class DescriptionJson(
    @SerializedName("en")
    val en: String
)


@Keep
data class MarketCapJson(
    @SerializedName("usd")
    val usd: String
)

@Keep
data class MarketDataJson(
    @SerializedName("current_price")
    val currentPrice: MarketCapJson,
    @SerializedName("market_cap_rank")
    val marketCapRank: String
)

@Keep
data class CoinImageJson(
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("large")
    val large: String,
)