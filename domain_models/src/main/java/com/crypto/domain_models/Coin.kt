package com.crypto.domain_models

import androidx.compose.runtime.Stable

@Stable
data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: String,
    val marketCap: String
) {
    fun getCurrentPriceText(): String {
        return if (currentPrice > 1) {
            "$${currentPrice}"
        } else {
            "$${String.format("%.3f", currentPrice)}"
        }
    }
}