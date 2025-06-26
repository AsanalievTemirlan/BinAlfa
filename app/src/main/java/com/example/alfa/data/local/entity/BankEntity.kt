package com.example.alfa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class BankEntity(
    val city: String,
    val bankName: String,
    val phone: String,
    val url: String
)


