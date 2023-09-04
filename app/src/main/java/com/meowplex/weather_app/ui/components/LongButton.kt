package com.meowplex.weather_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meowplex.weather_app.R

@Preview
@Composable
fun LongButton(
    modifier: Modifier = Modifier,
    title: String = "",
    value: String? = null,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                value?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                )
            }

        }
    }
}