package com.example.alfa.data.network.dto


import com.example.alfa.domain.model.BinModel
import com.google.gson.annotations.SerializedName

data class BinBaseResponse(
    @SerializedName("bank")
    val bank: Bank,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("country")
    val country: Country,
    @SerializedName("number")
    val number: Number,
    @SerializedName("prepaid")
    val prepaid: Boolean,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String
)

fun BinBaseResponse.toBinModel(): BinModel {
    return BinModel(
        bankModel = bank.toBankModel(),
        brand = this.brand ?: "Unknown Brand",
        countryModel = country.toCountryModel(),
        numberModel = number.toNumberModel(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Unknown Scheme",
        type = this.type ?: "Unknown Type"
    )
}

fun BinModel.toBinBaseResponse(): BinBaseResponse {
    return BinBaseResponse(
        bank = bankModel.toBank(),
        brand = this.brand ?: "Unknown Brand",
        country = countryModel.toCountry(),
        number = numberModel.toNumber(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Unknown Scheme",
        type = this.type ?: "Unknown Type"
    )
}



