package com.cesarwillymc.technicaltest99minutes.ui.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.DeletePlaceUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.GetPlaceIdUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.SavePlaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val savePlaceUseCase: SavePlaceUseCase,
    private val deletePlaceUseCase: DeletePlaceUseCase,
    private val getPlaceId: GetPlaceIdUseCase
) : ViewModel()
