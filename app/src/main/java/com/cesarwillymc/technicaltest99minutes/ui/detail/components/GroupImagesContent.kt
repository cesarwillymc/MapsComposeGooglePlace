package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.cesarwillymc.technicaltest99minutes.extension.ONE
import com.cesarwillymc.technicaltest99minutes.extension.ONE_NEGATIVE
import com.cesarwillymc.technicaltest99minutes.ui.theme.ImageLarge
import com.cesarwillymc.technicaltest99minutes.ui.theme.ImageMini
import com.cesarwillymc.technicaltest99minutes.ui.theme.Platinum
import com.cesarwillymc.technicaltest99minutes.ui.theme.ThreeDp
import com.cesarwillymc.technicaltest99minutes.ui.utils.onChangeIndexByRange

/**
 * Created by Willy on 11/17/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalComposeUiApi
@Composable
fun GroupImagesContent(
    modifier: Modifier = Modifier,
    images: List<String>,
    selectImageIndex: Int,
    onClickSelect: (Int) -> Unit,
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        /* Zoomable primary image */
        ZoomImage(
            imageUrl = images[selectImageIndex],
            height = ImageLarge,
            currentIndexImg = selectImageIndex,
            onSwipeLeft = {
                val currentIndex = images.onChangeIndexByRange(selectImageIndex, ONE)
                onClickSelect(currentIndex)
            },
            onSwipeRight = {
                val currentIndex = images.onChangeIndexByRange(selectImageIndex, ONE_NEGATIVE)
                onClickSelect(currentIndex)
            }
        )
    }

    Divider(color = Platinum, modifier = Modifier.padding(bottom = ThreeDp))

    if (images.size > ONE) {
        CarouselImages(
            modifier = modifier,
            images = images,
            size = ImageMini,
            actualValue = selectImageIndex,
            onClickSelect = onClickSelect
        )
    }
}
