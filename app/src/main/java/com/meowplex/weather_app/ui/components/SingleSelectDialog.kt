package com.meowplex.weather_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.meowplex.weather_app.R
import com.meowplex.weather_app.ui.theme.Dimensions

@Composable
fun SingleSelectDialog(
    modifier: Modifier = Modifier,
    title: String,
    optionsList: List<String>,
    defaultSelected: Int,
    onSelect: (index: Int) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    val selectedOption = remember {
        mutableStateOf(defaultSelected)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            modifier = modifier,
            color = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(
                    Dimensions.spaceMedium,
                )
            ) {

                Text(
                    title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(content = {
                    items(
                        count = optionsList.size,
                        itemContent = {
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()

                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = Dimensions.spaceSmall)
                                        .clickable {
                                            selectedOption.value = it
                                            onSelect(it)
                                        }
                                ) {
                                    Text(
                                        optionsList[it],
                                        style = MaterialTheme.typography.body1,
                                        color = MaterialTheme.colors.onSurface
                                    )
                                    val imageRes = if (selectedOption.value == it)
                                        R.drawable.ic_radio_checked
                                    else R.drawable.ic_radio_unchecked
                                    Image(
                                        modifier = Modifier.size(25.dp),
                                        painter = painterResource(id = imageRes),
                                        contentDescription = "",
                                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                                    )
                                }
                                if (it != optionsList.size - 1)
                                    Divider()
                            }

                        }
                    )
                })

                TextButton(
                    onClick = onDismissRequest,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        "CANCEL",
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.primary
                    )
                }

            }
        }
    }
}
