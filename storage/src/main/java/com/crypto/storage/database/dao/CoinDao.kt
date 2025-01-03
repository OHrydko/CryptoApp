package com.crypto.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crypto.storage.database.entity.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(cardInfo: List<CoinEntity>)

    @Query("SELECT * from coin_entity order by marketCapRank")
    fun getCoins(): Flow<List<CoinEntity>>

    @Query("DELETE from coin_entity")
    fun clearCoins()

}