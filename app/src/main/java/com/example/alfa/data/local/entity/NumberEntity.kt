package com.example.alfa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alfa.domain.model.NumberModel

@Entity(tableName = "number")
data class NumberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val length: Int,
    val luhn: Boolean
)