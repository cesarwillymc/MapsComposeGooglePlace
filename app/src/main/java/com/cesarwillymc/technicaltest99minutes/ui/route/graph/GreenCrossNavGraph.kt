package com.cesarwillymc.technicaltest99minutes.ui.route.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cesarwillymc.technicaltest99minutes.ui.detail.DetailScreen
import com.cesarwillymc.technicaltest99minutes.ui.home.HomeScreen
import com.cesarwillymc.technicaltest99minutes.ui.route.HomeRoute
import com.cesarwillymc.technicaltest99minutes.ui.route.action.HomeAction

@SuppressWarnings("LongMethod")
@Composable
fun GreenCrossNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    onClickSettings: () -> Unit
) {
    // Actions Route
    val homeActions = remember(navController) { HomeAction(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(HomeRoute.Home.path) {
            HomeScreen(
                navigateToDetail = homeActions.navigateToDetail,
                navigateToSetting = onClickSettings
            )
        }

        composable(HomeRoute.Detail.path) {
            DetailScreen(
                navigateUp = homeActions.upPress,
                detailViewModel = hiltViewModel()
            )
        }
    }
}
