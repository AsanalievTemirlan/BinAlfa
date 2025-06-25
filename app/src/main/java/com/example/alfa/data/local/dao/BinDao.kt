package com.example.alfa.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alfa.data.local.entity.BinEntity
import kotlinx.coroutines.flow.Flow

interface BinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBin(binEntity: BinEntity)

    @Query("SELECT * FROM bin")
    fun getAllCard(): Flow<List<BinEntity>>

}