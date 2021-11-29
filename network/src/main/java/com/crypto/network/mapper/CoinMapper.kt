package com.crypto.network.mapper

import com.crypto.domain_models.*
import com.crypto.network.model.response.CoinDetailJson
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

fun CoinDetailJson.toDomain(): CoinDetails {
    return CoinDetails(
        id = id,
        name = name,
        symbol = symbol,
        image = CoinImage(thumb = image.thumb, large = image.large, small = image.small),
        marketData = MarketData(
            currentPrice = MarketCap(usd = marketData.currentPrice.usd),
            marketCapRank = marketData.marketCapRank
        ),
        description = Description(description.en)
    )
}