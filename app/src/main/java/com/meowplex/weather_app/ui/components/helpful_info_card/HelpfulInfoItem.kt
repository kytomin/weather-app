package com.meowplex.weather_app.ui.components.helpful_info_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.meowplex.weather_app.ui.theme.Dimensions


@Composable
fun HelpfulInfoItem(title: String, text: String, resId: Int) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(Dimensions.spaceSmall)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = resId),
                contentDescription = "",
                modifier = Modifier
                    .padding(vertical = Dimensions.spaceExtraSmall)
                    .width(30.dp)
                    .height(30.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}