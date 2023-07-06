package com.sajjadio.quickshop.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formattedDate(): String? {
     val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
     val date =  format.parse(this)
     val formattedDate = date?.let { DateFormat.getDateInstance().format(it) }
    return date?.let { DateFormat.getDateInstance().format(it) }
}