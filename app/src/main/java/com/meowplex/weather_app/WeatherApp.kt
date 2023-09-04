package com.meowplex.weather_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.navigation.Routes
import com.meowplex.weather_app.ui.theme.WeatherAppTheme

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun WeatherApp() {
    WeatherAppTheme {
        val navController = rememberNavController()
        val router: Router = remember { Router(navController) }
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null,
        ) {
            NavigationContainer(
                navController = navController,
                router = router,
                startDestination = Routes.MAIN()
            )
        }

    }
}
