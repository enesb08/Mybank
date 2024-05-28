package com.enesbaskaya.mybank.util

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openGoogleMapWithAddress(address: String){
    val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(this.packageManager) != null) {
        this.startActivity(mapIntent)
    } else {
        val fallbackIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        this.startActivity(fallbackIntent)
    }
}

