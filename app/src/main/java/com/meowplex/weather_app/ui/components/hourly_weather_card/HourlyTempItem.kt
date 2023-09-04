package com.meowplex.weather_app.ui.components.hourly_weather_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.meowplex.weather_app.R
import com.meowplex.weather_app.ext.getResId
import com.meowplex.weather_app.model.HourlyWeatherModel
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.ui.theme.Dimensions


@Composable
fun HourlyTempItem(model: HourlyWeatherModel, tempUnit: TemperatureUnits) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(Dimensions.spaceMedium)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = model.time, style = MaterialTheme.typography.body1)
            Text(text = model.humidity, style = MaterialTheme.typography.caption)
            Image(
                painter = painterResource(R.drawable::class.java.getResId(model.iconRes)),
                contentDescription = "",
                modifier = Modifier.padding(vertical = Dimensions.spaceExtraSmall)
            )
            Text(text = model.temp.getValue(tempUnit), style = MaterialTheme.typography.body2)

        }
    }
}