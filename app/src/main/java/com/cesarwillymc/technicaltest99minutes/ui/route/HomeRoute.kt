package com.cesarwillymc.technicaltest99minutes.ui.route

import com.cesarwillymc.technicaltest99minutes.ui.route.action.HomeAction.Companion.MODEL_PRODUCT_ARGS

sealed class HomeRoute(path: String) : Route(path) {
    object Home : HomeRoute("home")
    object Detail : HomeRoute("home/{$MODEL_PRODUCT_ARGS}")
}
