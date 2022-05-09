package hu.bme.vik.tbs.zooanimalapp.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import hu.bme.vik.tbs.zooanimalapp.ui.animals.Animals
import hu.bme.vik.tbs.zooanimalapp.ui.details.AnimalDetails

@Composable
fun ZooAnimalMainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) {
                Animals(
                    viewModel = hiltViewModel(),
                    selectAnimal = {
                        navController.navigate("${NavScreen.AnimalDetails.route}/$it")
                    }
                )
            }
            composable(
                route = NavScreen.AnimalDetails.routeWithArgument,
                arguments = listOf(
                    navArgument(NavScreen.AnimalDetails.argument0) { type = NavType.LongType }
                )
            ) { backStackEntry ->
                val posterId =
                    backStackEntry.arguments?.getLong(NavScreen.AnimalDetails.argument0) ?: return@composable

                AnimalDetails(animalId = posterId, viewModel = hiltViewModel()) {
                    navController.navigateUp()
                }
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object AnimalDetails : NavScreen("AnimalDetails") {

        const val routeWithArgument: String = "AnimalDetails/{animalId}"

        const val argument0: String = "animalId"
    }
}