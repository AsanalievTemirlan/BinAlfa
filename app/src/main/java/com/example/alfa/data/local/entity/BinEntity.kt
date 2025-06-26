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
    val type: String
)

fun BankEntity.toBankModel(): BankModel {
    return BankModel(
        city = this.city ?: "Unknown City",
        name = this.bankName ?: "Unknown Name",
        phone = this.phone ?: "Unknown Phone",
        url = this.url ?: "Unknown URL"
    )
}

fun BankModel.toBankEntity(): BankEntity {
    return BankEntity(
        city = this.city ?: "Unknown City",
        bankName = this.name ?: "Unknown Name",
        phone = this.phone ?: "Unknown Phone",
        url = this.url ?: "Unknown URL"
    )
}

fun CountryEntity.toCountryModel(): CountryModel {
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

fun CountryModel.toCountryEntity(): CountryEntity {
    return CountryEntity(
        alpha2 = this.alpha2 ?: "Unknown Alpha2",
        currency = this.currency ?: "Unknown Currency",
        emoji = this.emoji ?: "Unknown Emoji",
        latitude = this.latitude ?: 0,
        longitude = this.longitude ?: 0,
        name = this.name ?: "Unknown Name",
        numeric = this.numeric ?: "Unknown Numeric"
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
        brand = this.brand ?: "Unknown Brand",
        countryModel = countryEntity.toCountryModel(),
        numberModel = numberEntity.toNumberModel(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Unknown Scheme",
        type = this.type ?: "Unknown Type"
    )
}

fun BinModel.toBinEntity(): BinEntity {
    return BinEntity(
        bankEntity = bankModel.toBankEntity(),
        brand = this.brand ?: "Unknown Brand",
        countryEntity = countryModel.toCountryEntity(),
        numberEntity = numberModel.toNumberEntity(),
        prepaid = this.prepaid,
        scheme = this.scheme ?: "Unknown Scheme",
        type = this.type ?: "Unknown Type"
    )
}