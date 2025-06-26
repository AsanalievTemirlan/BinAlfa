package com.example.alfa.data.network.dto


import com.example.alfa.domain.model.BankModel
import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.model.CountryModel
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

fun Bank.toBankModel(): BankModel {
    return BankModel(
        name = this.name ?: "Название банка не указано",
        url = this.url ?: "Сайт не указан",
        phone = this.phone ?: "Телефон не указан",
        city = this.city ?: "Город не указан"
    )
}

fun BankModel.toBank(): Bank {
    return Bank(
        name = this.name ?: "Название банка не указано",
        url = this.url ?: "Сайт не указан",
        phone = this.phone ?: "Телефон не указан",
        city = this.city ?: "Город не указан"
    )
}

fun BinBaseResponse.toBinModel(): BinModel {
    return BinModel(
        bankModel = bank.toBankModel(),
        brand = this.brand ?: "Бренд не указан",
        countryModel = country.toCountryModel(),
        numberModel = number.toNumberModel(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Схема не указана",
        type = this.type ?: "Тип не указан"
    )
}

fun BinModel.toBinBaseResponse(): BinBaseResponse {
    return BinBaseResponse(
        bank = bankModel.toBank(),
        brand = this.brand ?: "Бренд не указан",
        country = countryModel.toCountry(),
        number = numberModel.toNumber(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Схема не указана",
        type = this.type ?: "Тип не указан"
    )
}

fun Country.toCountryModel(): CountryModel {
    return CountryModel(
        alpha2 = this.alpha2 ?: "Код страны не указан",
        currency = this.currency ?: "Валюта не указана",
        emoji = this.emoji ?: "Флаг отсутствует",
        latitude = this.latitude ?: 0,
        longitude = this.longitude ?: 0,
        name = this.name ?: "Название страны не указано",
        numeric = this.numeric ?: "Цифровой код не указан"
    )
}

fun CountryModel.toCountry(): Country {
    return Country(
        alpha2 = this.alpha2 ?: "Код страны не указан",
        currency = this.currency ?: "Валюта не указана",
        emoji = this.emoji ?: "Флаг отсутствует",
        latitude = this.latitude ?: 0,
        longitude = this.longitude ?: 0,
        name = this.name ?: "Название страны не указано",
        numeric = this.numeric ?: "Цифровой код не указан"
    )
}


