package com.meowplex.weather_app.ui.screens.manage_cities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.meowplex.weather_app.R
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.ui.components.CityPreview
import com.meowplex.weather_app.ui.components.SwipeDismissItem
import com.meowplex.weather_app.ui.theme.Dimensions

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ManageCitiesScreen(
    viewModel: ManageCitiesViewModel = hiltViewModel(),
    router: Router
) {

    val uiState by viewModel.uiState


    LaunchedEffect(true) {
        viewModel.load()
    }

    val pullRefreshState = rememberPullRefreshState(false, { viewModel.load() })

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
                Box(
                    modifier = Modifier
                        .padding(Dimensions.spaceSmall)
                ) {
                    Text(
                        text = stringResource(R.string.manage_cities),
                        style = MaterialTheme.typography.h6
                    )
                }

            }
        },
        bottomBar = {
            BottomAppBar(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = { viewModel.onAddCity(router) },
                        modifier = Modifier
                            .shadow(0.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            hoveredElevation = 0.dp,
                            focusedElevation = 0.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.background,
                            contentColor = MaterialTheme.colors.onBackground
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "",
                                modifier = Modifier.size(18.dp)
                            )
                            Text(stringResource(id = R.string.add_city))
                        }

                    }
                }

            }
        }
    ) { pv ->

        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .fillMaxSize()
        ) {


            LazyColumn(
                modifier = Modifier
                    .padding()
                    .padding(
                        start = Dimensions.spaceSmall,
                        end = Dimensions.spaceSmall,
                        bottom = pv.calculateBottomPadding()
                    )
                    .fillMaxWidth(),

                content = {

                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.onAddCity(router) },

                            readOnly = true,
                            enabled = false,
                            value = "",
                            onValueChange = {},

                            textStyle = MaterialTheme.typography.body1,
                            shape = RoundedCornerShape(Dimensions.spaceSmall),

                            placeholder = { Text(text = stringResource(id = R.string.search)) },

                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = MaterialTheme.colors.background,
                            ),
                        )
                    }

                    itemsIndexed(
                        items = uiState.cities,
                        key = { _, city ->
                            city.id
                        },
                        itemContent = { index, city ->
                            SwipeDismissItem(content = {
                                CityPreview(
                                    model = city.weather,
                                    uiState.tempUnit,
                                    onClick = {
                                        viewModel.onSelectCity(router, city)
                                    },
                                    isPrimary = city.isPrimary
                                )
                            }, onDismissed = {
                                viewModel.onRemoveCity(
                                    router = router,
                                    id = city.id,
                                    index = index
                                )
                            })
                        }
                    )
                }
            )

            PullRefreshIndicator(
                refreshing = false, state = pullRefreshState, modifier = Modifier.align(
                    Alignment.TopCenter
                )
            )


        }

    }
}