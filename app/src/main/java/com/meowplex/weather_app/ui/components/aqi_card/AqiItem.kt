package com.meowplex.weather_app.ui.components.aqi_card

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.meowplex.weather_app.ui.theme.Dimensions

@Composable
fun AqiItem(title: String, body: String) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(Dimensions.spaceExtraSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.body1)
        Text(body, style = MaterialTheme.typography.body2)
    }
}