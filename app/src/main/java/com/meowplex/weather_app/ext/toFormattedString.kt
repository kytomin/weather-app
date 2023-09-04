package com.meowplex.weather_app.ext

import java.text.SimpleDateFormat
import java.util.*

fun Double.toFormattedString(): String {
    var str = String.format("%.0f", this)
    if (str == "-0")
        str = "0"
    return str
}


fun Date.toFormattedString(pattern: String = "dd/MM"): String {
    val sdf = SimpleDateFormat(pattern)
    return sdf.format(this)
}