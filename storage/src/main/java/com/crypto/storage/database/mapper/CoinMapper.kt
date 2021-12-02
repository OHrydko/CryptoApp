package com.crypto.storage.database.mapper

import com.crypto.domain_models.Coin
import com.crypto.storage.database.entity.CoinEntity

fun Coin.toEntity(): CoinEntity {
    return CoinEntity(
        coinId = id,
        coinName = name,
        symbol = symbol,
        image = image,
        currentPrice = currentPrice,
        marketCapRank = marketCapRank,
        marketCap = marketCap
    )
}

fun CoinEntity.toDomain(): Coin {
    return Coin(
        id = coinId,
        name = coinName,
        symbol = symbol,
        image = image,
        currentPrice = currentPrice,
        marketCapRank = marketCapRank,
        marketCap = marketCap
    )
}