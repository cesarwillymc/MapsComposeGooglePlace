package com.cesarwillymc.technicaltest99minutes.ui.route.action

import androidx.navigation.NavHostController
import com.cesarwillymc.technicaltest99minutes.ui.route.HomeRoute

class HomeAction(navController: NavHostController) {

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val navigateToHome: () -> Unit = {
        navController.navigate(HomeRoute.Home.path)
    }

    val navigateToDetail: (String) -> Unit = { model ->
        val routeReplace = HomeRoute.Detail.path.replace(
            "{$MODEL_PRODUCT_ARGS}",
            model
        )
        navController.navigate(routeReplace)
    }

    companion object {
        const val MODEL_PRODUCT_ARGS = "id"
    }
}
