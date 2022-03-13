package com.cesarwillymc.technicaltest99minutes.ui.home.entities

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isComplete: Boolean = false,
    val listData: List<PlacePresentation> = listOf()
)
