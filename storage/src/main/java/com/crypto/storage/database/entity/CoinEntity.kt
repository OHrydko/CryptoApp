package com.crypto.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COIN_ENTITY")
class CoinEntity(
    @PrimaryKey
    val coinId: String,
    val coinName: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: String,
    val marketCap: String
)