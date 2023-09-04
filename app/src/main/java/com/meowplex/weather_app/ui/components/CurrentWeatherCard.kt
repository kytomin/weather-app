package com.meowplex.weather_app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.meowplex.weather_app.model.CurrentWeatherModel
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.ui.theme.Dimensions

@Composable
fun CurrentWeatherCard(
    modifier: Modifier = Modifier,
    model: CurrentWeatherModel,
    tempUnit: TemperatureUnits
) {

    Box(
        modifier = modifier
            .wrapContentHeight()
            .padding(bottom = Dimensions.spaceMedium)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = model.temp.getValue(tempUnit), style = MaterialTheme.typography.h1)
            Text(
                text = "${model.maxTemp.getValue(tempUnit)} / ${model.minTemp.getValue(tempUnit)}",
                style = MaterialTheme.typography.body1
            )
            Text(text = model.comment, style = MaterialTheme.typography.body2)
        }

    }
}