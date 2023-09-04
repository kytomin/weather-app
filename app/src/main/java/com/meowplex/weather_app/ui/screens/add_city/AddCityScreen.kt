package com.meowplex.weather_app.ui.screens.add_city

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.meowplex.weather_app.R
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.ui.theme.Dimensions

@Composable
fun AddCityScreen(
    viewModel: AddCityViewModel = hiltViewModel(),
    router: Router,
    primaryCityExists: Boolean
) {

    val uiState by viewModel.uiState

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
                Box(modifier = Modifier.padding(Dimensions.spaceSmall)) {
                    Text(
                        text = stringResource(R.string.add_city),
                        style = MaterialTheme.typography.h6
                    )
                }

            }
        },

        ) {
        it
        Column(
            modifier = Modifier
                .padding(Dimensions.spaceSmall)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimensions.spaceSmall),

                readOnly = false,
                value = uiState.inputValue,
                onValueChange = { str -> viewModel.onInputChange(str) },

                textStyle = MaterialTheme.typography.body1,
                shape = RoundedCornerShape(Dimensions.spaceSmall),
                singleLine = true,

                placeholder = { Text(text = stringResource(R.string.search)) },

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = MaterialTheme.colors.background,
                ),
            )
            if (!primaryCityExists)
                TextButton(
                    onClick = { viewModel.onCurrentLocation(router) },
                    modifier = Modifier
                        .padding(bottom = Dimensions.spaceSmall)
                        .fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_location),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.size(Dimensions.spaceSmall))

                        Text(
                            text = uiState.let {
                                if (uiState.loadingCurrentCity) {
                                    stringResource(R.string.loading)
                                } else if (uiState.currentCity != null) {
                                    uiState.currentCity!!.city
                                } else stringResource(R.string.current_location)
                            },
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface
                        )

                    }


                }
            LazyColumn {
                items(count = uiState.cities.size) { index ->
                    val city = uiState.cities[index]
                    Text(
                        "${city.city}, ${city.region}, ${city.country}",
                        modifier = Modifier
                            .padding(
                                horizontal = Dimensions.spaceSmall,
                                vertical = Dimensions.spaceMedium,
                            )
                            .clickable {
                                viewModel.onAddCity(city, router)
                            },
                        style = MaterialTheme.typography.body1,

                        )
                }
            }

        }

    }
}