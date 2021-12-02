package com.crypto.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crypto.storage.database.AppDatabase.Companion.VERSION
import com.crypto.storage.database.dao.CoinDao
import com.crypto.storage.database.entity.CoinEntity

@Database(
    version = VERSION,
    exportSchema = false,
    entities = [
        CoinEntity::class
    ],
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCoinDao(): CoinDao

    companion object {
        const val VERSION = 1
        const val NAME = "database"
    }
}