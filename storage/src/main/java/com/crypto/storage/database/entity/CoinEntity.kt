package com.crypto.storage.database.entity

import androidx.room.Entity

@Entity(tableName = "COIN_ENTITY", primaryKeys = ["coinId", "marketCapRank"])
class CoinEntity(
    val coinId: String,
    val coinName: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: String,
    val marketCap: String
)