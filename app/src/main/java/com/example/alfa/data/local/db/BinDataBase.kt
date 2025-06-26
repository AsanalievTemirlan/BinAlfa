package com.example.alfa.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alfa.data.local.dao.BinDao
import com.example.alfa.data.local.entity.BinEntity

@Database(entities = [BinEntity::class], version = 2)
abstract class BinDataBase: RoomDatabase() {
    abstract fun dao(): BinDao
}