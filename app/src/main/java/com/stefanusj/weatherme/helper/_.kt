package com.stefanusj.weatherme.helper

import java.text.SimpleDateFormat
import java.util.*

fun Long.toCurrentDate(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    val date = Date(this)
    return sdf.format(date)
}