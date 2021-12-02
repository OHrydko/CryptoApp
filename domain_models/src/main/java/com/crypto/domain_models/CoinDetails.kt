package com.crypto.domain_models

data class CoinDetails(
    val id: String,
    val symbol: String,
    val name: String,
    val description: Description,
    val image: CoinImage,
    val marketData: MarketData
) {
    companion object {
        val emptyState = CoinDetails(
            id = "",
            symbol = "",
            name = "",
            description = Description(en = ""),
            image = CoinImage(thumb = "", small = "", large = ""),
            marketData = MarketData(currentPrice = MarketCap(usd = ""), marketCapRank = "")
        )
    }
}


data class Description(
    var en: String
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