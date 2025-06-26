package com.example.alfa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alfa.domain.model.BankModel

@Entity(tableName = "bank")
data class BankEntity(
    @PrimaryKey(autoGenerate = true)
    val bankId: Long = 0,
    val city: String,
    val bankName: String,
    val phone: String,
    val url: String
)


