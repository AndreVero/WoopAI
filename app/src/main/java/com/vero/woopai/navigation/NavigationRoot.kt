package com.vero.woopai.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vero.woopai.features.history.presentation.HistoryScreen
import com.vero.woopai.features.home.presentation.HomeScreen
import com.vero.woopai.features.info.presentation.InfoScreen

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
            HomeScreen(
                openHistoryScreen = { navHostController.navigate(Screens.HISTORY) },
                openInfoScreen = { navHostController.navigate(Screens.INFO) }
            )
        }
        composable(
            route = Screens.HISTORY,
        ) {
            HistoryScreen(navigateBack = { navHostController.popBackStack()})
        }
        composable(
            route = Screens.INFO,
        ) {
            InfoScreen(
                openHomeScreen = { navHostController.navigate(Screens.HOME) },
                navigateBack = { navHostController.popBackStack()}
            )
        }
    }

}