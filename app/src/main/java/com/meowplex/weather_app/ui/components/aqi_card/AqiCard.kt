package com.meowplex.weather_app.ui.components.aqi_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.meowplex.weather_app.R
import com.meowplex.weather_app.model.AqiModel
import com.meowplex.weather_app.ui.theme.Dimensions
import com.meowplex.weather_app.ui.theme.Shapes

@Composable
fun AqiCard(model: AqiModel) {
    Column(modifier = Modifier.wrapContentHeight()) {
        Text(
            stringResource(id = R.string.air_quality),
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(Dimensions.spaceMedium)
                .align(alignment = Alignment.Start)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = Dimensions.spaceMedium)
                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.2f), Shapes.small)
        ) {

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = Dimensions.spaceMedium, vertical = Dimensions.spaceSmall),
                horizontalAlignment = Alignment.Start
            ) {
                AqiItem(
                    title = stringResource(id = R.string.aqi_index),
                    body = stringResource(id = model.comment)
                )
                AqiItem(title = stringResource(id = R.string.co), body = model.co)
                AqiItem(title = stringResource(id = R.string.o3), body = model.o3)
                AqiItem(title = stringResource(id = R.string.no2), body = model.no2)
                AqiItem(title = stringResource(id = R.string.so2), body = model.so2)
            }
        }

    }
}