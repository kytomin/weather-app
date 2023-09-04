package com.meowplex.weather_app.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.meowplex.weather_app.R
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.ui.components.CurrentWeatherCard
import com.meowplex.weather_app.ui.components.aqi_card.AqiCard
import com.meowplex.weather_app.ui.components.astro_card.SunriseCard
import com.meowplex.weather_app.ui.components.daily_weather_card.DailyWeatherCard
import com.meowplex.weather_app.ui.components.helpful_info_card.HelpfulInfoCard
import com.meowplex.weather_app.ui.components.hourly_weather_card.HourlyWeatherCard
import com.meowplex.weather_app.ui.theme.Dimensions

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    router: Router,
    latitude: Double?,
    longitude: Double?,
    isPrimaryCity: Boolean
) {
    val uiState by viewModel.uiState

    LaunchedEffect(true) {
        if (latitude != null && longitude != null) {
            viewModel.load(latitude, longitude, isPrimaryCity)
        } else {
            viewModel.load(router)
        }
    }

    val pullRefreshState = rememberPullRefreshState(false, { viewModel.onRetry() })

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                uiState.currentWeather?.let { current ->

                    MediumTopAppBar(
                        title = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomCenter
                            ) {

                                androidx.compose.material3.Text(
                                    current.city,
                                    style = MaterialTheme.typography.h2,
                                )

                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colors.background,
                            scrolledContainerColor = MaterialTheme.colors.background,
                            titleContentColor = MaterialTheme.colors.onBackground
                        ),
                        navigationIcon = {
                            Box(modifier = Modifier.width(52.dp))
                        },
                        actions = {
                            IconButton(
                                onClick = { expanded = true },
                                modifier = Modifier
                                    .width(40.dp)
                                    .padding(0.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_more),
                                    modifier = Modifier.size(30.dp),
                                    contentDescription = "",
                                )

                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    DropdownMenuItem(onClick = { viewModel.onSettings(router) }) {
                                        Text(
                                            stringResource(R.string.settings),
                                            modifier = Modifier.padding(horizontal = Dimensions.spaceSmall)
                                        )
                                    }
                                    DropdownMenuItem(onClick = { viewModel.onManageCities(router) }) {
                                        Text(
                                            stringResource(R.string.manage_cities),
                                            modifier = Modifier.padding(horizontal = Dimensions.spaceSmall)
                                        )
                                    }
                                }
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            },
        ) { pv ->
            pv

            if (uiState.isLoading)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                uiState.error?.let {
                    Text("${stringResource(R.string.error)}: $it")
                    Button(onClick = { viewModel.onRetry() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_reload),
                            contentDescription = ""
                        )
                    }
                }

                uiState.currentWeather?.let {
                    CurrentWeatherCard(
                        model = it,
                        modifier = Modifier.alpha(1 - scrollBehavior.state.collapsedFraction),
                        tempUnit = uiState.tempUnit,

                        )
                }

                uiState.hourlyWeather.let {
                    if (it.isNotEmpty())
                        HourlyWeatherCard(it, uiState.tempUnit)
                }

                uiState.dailyWeather.let {
                    if (it.isNotEmpty())
                        DailyWeatherCard(it, uiState.tempUnit)
                }

                uiState.helpfulInfo?.let {
                    HelpfulInfoCard(model = it)
                }

                uiState.astro?.let {
                    SunriseCard(model = it)
                }

                uiState.aqi?.let {
                    AqiCard(model = it)
                }
            }
        }

        PullRefreshIndicator(
            false,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

