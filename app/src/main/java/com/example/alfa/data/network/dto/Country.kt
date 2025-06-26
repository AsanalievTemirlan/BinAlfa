package com.example.alfa.data.network.dto


import com.example.alfa.domain.model.CountryModel
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("alpha2")
    val alpha2: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("emoji")
    val emoji: String,
    @SerializedName("latitude")
    val latitude: Int,
    @SerializedName("longitude")
    val longitude: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("numeric")
    val numeric: String
)

