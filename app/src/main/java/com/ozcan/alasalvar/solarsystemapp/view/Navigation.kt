package com.ozcan.alasalvar.solarsystemapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ozcan.alasalvar.solarsystemapp.navigation.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.Details.route + "/{position}",
            arguments = listOf(
                navArgument("position") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            DetailScreen(position = entry.arguments?.getInt("position") ?: 0, navController)
        }
    }

}