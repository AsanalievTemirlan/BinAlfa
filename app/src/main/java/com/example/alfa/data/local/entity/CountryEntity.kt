package com.example.alfa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CountryEntity(
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Int,
    val longitude: Int,
    val name: String,
    val numeric: String
)

