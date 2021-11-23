package com.crypto.domain_models

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: String,
    val marketCapRank: String,
    val marketCap: String
)