package com.example.alfa.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alfa.domain.model.BankModel
import com.example.alfa.domain.model.BinModel
import com.example.alfa.domain.model.CountryModel
import com.example.alfa.domain.model.NumberModel

@Entity(tableName = "bin")
data class BinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @Embedded(prefix = "bank_")
    val bankEntity: BankEntity,

    @Embedded(prefix = "country_")
    val countryEntity: CountryEntity,

    @Embedded(prefix = "number_")
    val numberEntity: NumberEntity,

    val brand: String,
    val prepaid: Boolean,
    val scheme: String,
    val type: String,
    val bin: String,
)
fun BankEntity.toBankModel(): BankModel {
    return BankModel(
        city = this.city ?: "Город не указан",
        name = this.bankName ?: "Название банка не указано",
        phone = this.phone ?: "Телефон не указан",
        url = this.url ?: "Сайт не указан"
    )
}

fun BankModel.toBankEntity(): BankEntity {
    return BankEntity(
        city = this.city ?: "Город не указан",
        bankName = this.name ?: "Название банка не указано",
        phone = this.phone ?: "Телефон не указан",
        url = this.url ?: "Сайт не указан"
    )
}

fun CountryEntity.toCountryModel(): CountryModel {
    return CountryModel(
        alpha2 = this.alpha2 ?: "Код страны не указан",
        currency = this.currency ?: "Валюта не указана",
        emoji = this.emoji ?: "Флаг отсутствует",
        latitude = this.latitude ?: 0,
        longitude = this.longitude ?: 0,
        name = this.name ?: "Название страны не указано",
        numeric = this.numeric ?: "Код страны не указан"
    )
}

fun CountryModel.toCountryEntity(): CountryEntity {
    return CountryEntity(
        alpha2 = this.alpha2 ?: "Код страны не указан",
        currency = this.currency ?: "Валюта не указана",
        emoji = this.emoji ?: "Флаг отсутствует",
        latitude = this.latitude ?: 0,
        longitude = this.longitude ?: 0,
        name = this.name ?: "Название страны не указано",
        numeric = this.numeric ?: "Код страны не указан"
    )
}

fun NumberEntity.toNumberModel(): NumberModel {
    return NumberModel(
        length = this.length ?: 0,
        luhn = this.luhn ?: false
    )
}

fun NumberModel.toNumberEntity(): NumberEntity {
    return NumberEntity(
        length = this.length ?: 0,
        luhn = this.luhn ?: false
    )
}

fun BinEntity.toBinModel(): BinModel {
    return BinModel(
        bankModel = bankEntity.toBankModel(),
        brand = this.brand ?: "Бренд не указан",
        countryModel = countryEntity.toCountryModel(),
        numberModel = numberEntity.toNumberModel(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Схема не указана",
        type = this.type ?: "Тип не указан",
        bin = bin
    )
}

fun BinModel.toBinEntity(bin: String): BinEntity {
    return BinEntity(
        bankEntity = bankModel.toBankEntity(),
        brand = this.brand ?: "Бренд не указан",
        countryEntity = countryModel.toCountryEntity(),
        numberEntity = numberModel.toNumberEntity(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Схема не указана",
        type = this.type ?: "Тип не указан",
        bin = bin ?: "BIN не указан"
    )
}