package com.meowplex.weather_app.ui.components.astro_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meowplex.weather_app.R
import com.meowplex.weather_app.model.AstroModel
import com.meowplex.weather_app.ui.theme.Dimensions
import com.meowplex.weather_app.ui.theme.Shapes
import java.time.LocalTime

@Preview
@Composable
fun SunriseCard(
    model: AstroModel = AstroModel(
        LocalTime.of(1, 20),
        LocalTime.of(11, 20),
        LocalTime.of(2, 20),
        LocalTime.of(10, 20),
        LocalTime.now(),
        "TEST",
        true,
        true
    )
) {

    Column(modifier = Modifier.wrapContentHeight()) {
        Text(
            stringResource(id = R.string.sunrise_and_sunset),
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
                    .fillMaxWidth()
            ) {
                Box {
                    Box(
                        modifier = Modifier.padding(
                            top = Dimensions.spaceMedium,
                            start = Dimensions.spaceMedium,
                            end = Dimensions.spaceMedium
                        )
                    ) {
                        SunriseView(
                            imageId = R.drawable.sun,
                            progress = model.progressSun,
                            arcWidth = 4f,
                            arcColor = MaterialTheme.colors.onBackground.copy(alpha = 0.2f)
                        )
                    }
                    Box(
                        modifier = Modifier.padding(
                            top = Dimensions.spaceExtraLarge,
                            start = Dimensions.spaceExtraLarge,
                            end = Dimensions.spaceExtraLarge
                        )
                    ) {
                        SunriseView(
                            imageId = R.drawable.moon,
                            imageSide = 70,
                            progress = model.progressMoon,
                            arcWidth = 3f,
                            arcColor = MaterialTheme.colors.onBackground.copy(alpha = 0.2f)
                        )
                    }
                }
                Divider(
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.2f),
                    modifier = Modifier
                        .height(2.dp)
                        .padding(horizontal = Dimensions.spaceSmall)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(Dimensions.spaceSmall),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            "${stringResource(id = R.string.sunrise)} ${model.sunrise ?: "-"}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            "${stringResource(id = R.string.moonrise)} ${model.moonrise ?: "-"}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            stringResource(id = R.string.moon_phase),
                            style = MaterialTheme.typography.caption
                        )
                        Text(model.moonPhase, style = MaterialTheme.typography.caption)
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            "${stringResource(id = R.string.sunset)} ${model.sunset ?: "-"}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            "${stringResource(id = R.string.moonset)} ${model.moonset ?: "-"}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                }

            }


        }

    }
}