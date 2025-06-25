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

fun Country.toCountryModel(): CountryModel {
    return CountryModel(
        alpha2 = this.alpha2 ?: "Unknown Alpha2",
        currency = this.currency ?: "Unknown Currency",
        emoji = this.emoji ?: "Unknown Emoji",
        latitude = this.latitude ?: 0,
        longitude = this.longitude ?: 0,
        name = this.name ?: "Unknown Name",
        numeric = this.numeric ?: "Unknown Numeric"
    )
}

fun CountryModel.toCountry(): Country {
    return Country(
        alpha2 = this.alpha2 ?: "Unknown Alpha2",
        currency = this.currency ?: "Unknown Currency",
        emoji = this.emoji ?: "Unknown Emoji",
        latitude = this.latitude ?: 0,
        longitude = this.longitude ?: 0,
        name = this.name ?: "Unknown Name",
        numeric = this.numeric ?: "Unknown Numeric"
    )
}

