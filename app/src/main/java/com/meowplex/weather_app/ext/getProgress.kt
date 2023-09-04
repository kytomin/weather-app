package com.meowplex.weather_app.ext

import java.time.LocalTime

fun LocalTime.getProgress(startTime: LocalTime?, endTime: LocalTime?): Double {

    if (endTime == null) {
        return 0.5
    }
    if (startTime == null) {
        return 1.0
    }

    val startMinutes = startTime.hour * 60 + startTime.minute
    var endMinutes = endTime.hour * 60 + endTime.minute
    var nowMinutes = this.hour * 60 + this.minute


    if (endMinutes <= startMinutes) {
        if (nowMinutes < endMinutes) {
            nowMinutes += 24 * 60
        }
        endMinutes += 24 * 60
    }

    if (nowMinutes <= startMinutes)
        return 0.0

    if (nowMinutes >= endMinutes)
        return 1.0
    val diff1 = (nowMinutes - startMinutes).toDouble()
    val diff2 = (endMinutes - nowMinutes).toDouble()
    return diff1 / (diff1 + diff2)
}