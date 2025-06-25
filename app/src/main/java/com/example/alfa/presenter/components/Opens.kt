package com.example.alfa.presenter.components

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

fun openPhone(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
    context.startActivity(intent)
}

fun openMaps(context: Context, countryCode: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$countryCode"))
    context.startActivity(intent)
}