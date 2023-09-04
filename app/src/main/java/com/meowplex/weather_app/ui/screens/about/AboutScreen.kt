package com.meowplex.weather_app.ui.screens.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.meowplex.weather_app.BuildConfig
import com.meowplex.weather_app.R
import com.meowplex.weather_app.ui.components.LongButton
import com.meowplex.weather_app.ui.theme.Dimensions


@ExperimentalMaterial3Api
@Composable
fun AboutScreen() {

    val context = LocalContext.current
    val intent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kytomin/weather-app")) }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        stringResource(id = R.string.about),
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
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(R.string.version) + ": ${BuildConfig.VERSION_NAME}",
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.height(Dimensions.spaceLarge))
            LongButton(
                modifier = Modifier.padding(
                    horizontal = Dimensions.spaceMedium,
                    vertical = Dimensions.spaceSmall
                ),
                title = stringResource(R.string.source_code),
                onClick = {
                    context.startActivity(intent)
                }
            )
        }

    }
}