package com.example.alfa.data.network.dto


import com.example.alfa.domain.model.BankModel
import com.google.gson.annotations.SerializedName

data class Bank(
    @SerializedName("city")
    val city: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("url")
    val url: String
)

fun Bank.toBankModel(): BankModel {
    return BankModel(
        name = this.name ?: "Unknown Name",
        url = this.url ?: "Unknown URL",
        phone = this.phone ?: "Unknown Phone",
        city = this.city ?: "Unknown City"
    )
}

fun BankModel.toBank(): Bank {
    return Bank(
        name = this.name ?: "Unknown Name",
        url = this.url ?: "Unknown URL",
        phone = this.phone ?: "Unknown Phone",
        city = this.city ?: "Unknown City"
    )
}
