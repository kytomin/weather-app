package com.meowplex.weather_app.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.meowplex.weather_app.R
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.navigation.Routes
import com.meowplex.weather_app.ui.components.LongButton
import com.meowplex.weather_app.ui.components.SingleSelectDialog
import com.meowplex.weather_app.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    router: Router
) {

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {

        val options = TemperatureUnits.values()
        val selectedIndex = options.indexOf(viewModel.currentTempUnit.value)

        SingleSelectDialog(
            title = stringResource(R.string.temperature_unit),
            optionsList = options.map {
                stringResource(id = it.getNameResource())

            },
            defaultSelected = selectedIndex,
            onSelect = { index ->
                viewModel.onSelectTempUnit(options[index])
                showDialog.value = false
            },
            onDismissRequest = {
                showDialog.value = false
            },
        )
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.h2
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colors.background,
                    scrolledContainerColor = MaterialTheme.colors.background,
                    titleContentColor = MaterialTheme.colors.onBackground
                ),
            )
        }
    ) {
        it
        Column {
            LongButton(
                modifier = Modifier.padding(
                    horizontal = Dimensions.spaceMedium,
                    vertical = Dimensions.spaceSmall
                ),
                title = stringResource(id = R.string.temperature_unit),
                value = stringResource(
                    id = viewModel.currentTempUnit.value!!.getNameResource()
                ),
                onClick = {
                    showDialog.value = true
                }
            )
            LongButton(
                modifier = Modifier.padding(
                    horizontal = Dimensions.spaceMedium,
                    vertical = Dimensions.spaceSmall
                ),
                title = stringResource(R.string.about),
                onClick = {
                    router.navigate(Routes.ABOUT)
                }
            )
        }

    }
}