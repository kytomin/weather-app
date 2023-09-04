package com.meowplex.weather_app.ui.components.helpful_info_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.meowplex.weather_app.R
import com.meowplex.weather_app.model.HelpfulInfoModel
import com.meowplex.weather_app.ui.theme.Dimensions
import com.meowplex.weather_app.ui.theme.Shapes

@Composable
fun HelpfulInfoCard(model: HelpfulInfoModel) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Text(
            stringResource(id = R.string.helpful_indo),
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(Dimensions.spaceMedium)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = Dimensions.spaceMedium)
                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.2f), Shapes.small)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.height(190.dp),
                userScrollEnabled = false,
            ) {


                items(count = 5, itemContent = {
                    when (it) {
                        0 -> HelpfulInfoItem(
                            stringResource(id = R.string.humidity),
                            model.humidity,
                            R.drawable.ic_humidity
                        )
                        1 -> HelpfulInfoItem(
                            stringResource(id = R.string.feels_like),
                            model.feelsLike,
                            R.drawable.ic_feels_like
                        )
                        2 -> HelpfulInfoItem(
                            stringResource(id = R.string.wind_speed),
                            model.windSpeed,
                            R.drawable.ic_wind_speed
                        )
                        3 -> HelpfulInfoItem(
                            stringResource(id = R.string.wind_dir),
                            model.windDir,
                            R.drawable.ic_wind_dir
                        )
                        4 -> HelpfulInfoItem(
                            stringResource(id = R.string.uv_index),
                            model.uv,
                            R.drawable.ic_uv
                        )
                    }
                })

            }

        }

    }
}