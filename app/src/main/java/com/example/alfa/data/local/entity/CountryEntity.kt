package com.example.alfa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alfa.domain.model.CountryModel

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val countryId: Long = 0,
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Int,
    val longitude: Int,
    val countryName: String,
    val numeric: String
)

