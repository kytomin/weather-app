package com.meowplex.weather_app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.navigation.Routes
import com.meowplex.weather_app.ui.screens.about.AboutScreen
import com.meowplex.weather_app.ui.screens.add_city.AddCityScreen
import com.meowplex.weather_app.ui.screens.main.MainScreen
import com.meowplex.weather_app.ui.screens.manage_cities.ManageCitiesScreen
import com.meowplex.weather_app.ui.screens.settings.SettingsScreen

@ExperimentalMaterial3Api
@Composable
fun NavigationContainer(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    router: Router = remember { Router(navController) },
    startDestination: String = Routes.MAIN()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            Routes.MAIN(),
            arguments = listOf(
                navArgument("latitude") { type = NavType.StringType },
                navArgument("longitude") { type = NavType.StringType },
                navArgument("isPrimaryCity") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val latitude = backStackEntry.arguments?.getString("latitude")?.toDouble()
            val longitude = backStackEntry.arguments?.getString("longitude")?.toDouble()
            val isPrimaryCity = backStackEntry.arguments?.getBoolean("isPrimaryCity") ?: false
            MainScreen(
                router = router,
                latitude = latitude,
                longitude = longitude,
                isPrimaryCity = isPrimaryCity
            )
        }
        composable(Routes.MANAGE_CITIES) {
            ManageCitiesScreen(router = router)
        }
        composable(Routes.ADD_CITY(), arguments = listOf(
            navArgument("primaryCityExists") { type = NavType.BoolType }
        )) { backStackEntry ->
            val primaryCityExists =
                backStackEntry.arguments?.getBoolean("primaryCityExists") ?: true
            AddCityScreen(router = router, primaryCityExists = primaryCityExists)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(router = router)
        }
        composable(Routes.ABOUT) {
            AboutScreen()
        }
    }
}
