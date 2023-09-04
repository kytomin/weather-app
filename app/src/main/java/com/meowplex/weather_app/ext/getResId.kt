package com.meowplex.weather_app.ext

import androidx.annotation.DrawableRes
import com.meowplex.weather_app.R
import java.lang.reflect.Field

@DrawableRes
fun Class<in R.drawable>.getResId(resName: String): Int {
    return try {
        val idField: Field = this.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}