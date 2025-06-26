package com.example.alfa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number")
data class NumberEntity(
    @PrimaryKey(autoGenerate = true)
    val numberId: Long = 0,
    val length: Int,
    val luhn: Boolean
)