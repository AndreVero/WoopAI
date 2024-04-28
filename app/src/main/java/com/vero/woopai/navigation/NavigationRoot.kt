package com.vero.woopai.navigation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vero.woopai.features.history.presentation.HistoryScreen
import com.vero.woopai.features.home.presentation.HomeScreen
import com.vero.woopai.features.info.presentation.InfoScreen
import com.vero.woopai.ui.theme.BackgroundColor

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HOME,
        modifier = modifier.fillMaxSize().background(BackgroundColor),
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
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { size -> -(size * 2) },
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { size -> -(size * 2) },
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = LinearEasing
                    )
                )
            }
        ) {
            HistoryScreen(navigateBack = { navHostController.popBackStack()})
        }
        composable(
            route = Screens.INFO,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { size -> size * 2 },
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { size -> size * 2 },
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = LinearEasing
                    )
                )
            }
        ) {
            InfoScreen(
                openHomeScreen = { navHostController.navigate(Screens.HOME) },
                navigateBack = { navHostController.popBackStack()}
            )
        }
    }

}