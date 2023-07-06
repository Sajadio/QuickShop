package com.sajjadio.quickshop.utils

import android.content.Context
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formattedDate(): String? {
     val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
     val date =  format.parse(this)
    return date?.let { DateFormat.getDateInstance().format(it) }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}