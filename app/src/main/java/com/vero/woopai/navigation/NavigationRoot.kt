package com.vero.woopai.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vero.woopai.features.home.presentation.HomeScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HOME,
        modifier = modifier.fillMaxSize(),
    ) {
        composable(
            route = Screens.HOME,
        ) {
            HomeScreen(openHistoryScreen = { navHostController.navigate(Screens.HISTORY) })
        }
        composable(
            route = Screens.HISTORY,
        ) {
            Text(text = "HISTORY")
        }
        composable(
            route = Screens.WISH,
        ) {
            Text(text = "WISH")
        }
        composable(
            route = Screens.OUTCOME,
        ) {
            Text(text = "OUTCOME")
        }
        composable(
            route = Screens.OBSTACLE,
        ) {
            Text(text = "OBSTACLE")
        }
        composable(
            route = Screens.PLAN,
        ) {
            Text(text = "PLAN")
        }
    }

}