package com.crypto.domain_models

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: String,
    val marketCap: String
) {
    companion object {
        val default = Coin(
            id = "-1",
            name = "",
            symbol = "",
            image = "",
            currentPrice = 0.0,
            marketCap = "",
            marketCapRank = ""
        )
    }
}