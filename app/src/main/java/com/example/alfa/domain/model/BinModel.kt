package com.example.alfa.domain.model

data class BinModel(
    val bankModel: BankModel,
    val brand: String,
    val countryModel: CountryModel,
    val numberModel: NumberModel,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)