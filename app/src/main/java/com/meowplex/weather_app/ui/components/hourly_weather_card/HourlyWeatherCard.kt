package com.meowplex.weather_app.ui.components.hourly_weather_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.meowplex.weather_app.model.HourlyWeatherModel
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.ui.theme.Dimensions
import com.meowplex.weather_app.ui.theme.Shapes

@Composable
fun HourlyWeatherCard(model: List<HourlyWeatherModel>, tempUnit: TemperatureUnits) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Dimensions.spaceMedium)
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.2f), Shapes.small)
    ) {

        LazyRow(modifier = Modifier.wrapContentHeight()) {
            items(
                count = model.size,
                itemContent = {
                    HourlyTempItem(model[it], tempUnit)
                }
            )
        }
    }
}
