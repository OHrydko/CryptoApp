package com.crypto.domain_models

data class CoinDetails(
    val id: String,
    val symbol: String,
    val name: String,
    val description: Description,
    val image: CoinImage,
    val marketData: MarketData,
)

data class Description(
    val en: String
)

data class CoinImage(
    val thumb: String,
    val small: String,
    val large: String,
)

data class MarketCap(
    val usd: String
)

data class MarketData(
    val currentPrice: MarketCap,
    val marketCapRank: String
)