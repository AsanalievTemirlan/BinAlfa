package com.example.alfa.data.network.dto


import com.example.alfa.domain.model.NumberModel
import com.google.gson.annotations.SerializedName

data class Number(
    @SerializedName("length")
    val length: Int,
    @SerializedName("luhn")
    val luhn: Boolean
)

fun Number.toNumberModel(): NumberModel {
    return NumberModel(
        length = this.length ?: 0,
        luhn = this.luhn ?: false
    )
}

fun NumberModel.toNumber(): Number {
    return Number(
        length = this.length ?: 0,
        luhn = this.luhn ?: false
    )
}
