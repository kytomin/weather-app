package com.meowplex.weather_app.components

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
import com.meowplex.weather_app.model.DailyWeatherModel
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.ui.theme.Dimensions

@Composable
fun DailyTempItem(model: DailyWeatherModel, tempUnit: TemperatureUnits) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = Dimensions.spaceMedium,
                vertical = Dimensions.spaceSmall
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = model.dayOfWeek, style = MaterialTheme.typography.body1)
            Text(text = model.date, style = MaterialTheme.typography.caption)

            Image(
                painter = painterResource(
                    id = R.drawable::class.java.getResId(
                        model.iconRes,
                    )
                ),
                contentDescription = "",
                modifier = Modifier.padding(vertical = Dimensions.spaceExtraSmall)
            )
            Text(text = model.maxTemp.getValue(tempUnit), style = MaterialTheme.typography.body2)
            Text(text = model.minTemp.getValue(tempUnit), style = MaterialTheme.typography.body2)

        }
    }
}