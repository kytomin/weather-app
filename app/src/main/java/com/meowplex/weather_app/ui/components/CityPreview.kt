package com.meowplex.weather_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.meowplex.weather_app.R
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.model.WeatherPreviewModel
import com.meowplex.weather_app.ui.theme.Dimensions

@Composable
fun CityPreview(
    model: WeatherPreviewModel,
    tempUnit: TemperatureUnits,
    onClick: () -> Unit,
    isPrimary: Boolean
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .background(
                color = if (isPrimary) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant,
                shape = RoundedCornerShape(Dimensions.spaceSmall)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = Dimensions.spaceSmall, horizontal = Dimensions.spaceMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically) {
                Text(model.city)
                if (isPrimary) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.End
            ) {
                Text(model.temp.getValue(tempUnit), style = MaterialTheme.typography.body2)
                Text(model.comment, style = MaterialTheme.typography.caption)
            }
        }
    }
}