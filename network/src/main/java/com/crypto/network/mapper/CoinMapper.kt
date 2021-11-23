package com.crypto.network.mapper

import com.crypto.domain_models.Coin
import com.crypto.network.model.response.CoinJson

fun CoinJson.toDomain(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        image = image,
        currentPrice = currentPrice,
        marketCapRank = marketCapRank,
        marketCap = marketCap
    )
}