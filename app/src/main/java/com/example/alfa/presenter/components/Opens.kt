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

fun openMaps(context: Context, latitude: Int, longitude: Int) {
    val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
