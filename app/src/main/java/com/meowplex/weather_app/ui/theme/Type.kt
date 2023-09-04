package com.meowplex.weather_app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(

    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    h1 = TextStyle(
        fontSize = 60.sp
    ),
    h2 = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.W700
    ),
    h3 = TextStyle(
        fontSize = 24.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontSize = 14.sp
    )
)