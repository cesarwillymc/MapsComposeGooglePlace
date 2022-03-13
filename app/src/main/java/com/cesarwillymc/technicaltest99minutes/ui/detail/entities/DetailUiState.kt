package com.cesarwillymc.technicaltest99minutes.ui.detail.entities

import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class DetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: DetailPlace? = null
)
