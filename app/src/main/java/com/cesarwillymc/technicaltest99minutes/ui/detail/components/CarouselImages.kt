package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.cesarwillymc.technicaltest99minutes.extension.SIZE_60
import com.cesarwillymc.technicaltest99minutes.ui.theme.ForestGreen
import com.cesarwillymc.technicaltest99minutes.ui.theme.Platinum
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small50
import com.cesarwillymc.technicaltest99minutes.ui.theme.TwoDp
import com.cesarwillymc.technicaltest99minutes.ui.utils.getLinkPhotoGoogle

/**
 * Created by Willy on 11/17/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Composable
fun CarouselImages(
    modifier: Modifier = Modifier,
    images: List<String>,
    size: Dp,
    actualValue: Int,
    onClickSelect: (Int) -> Unit,

) {
    LazyRow(
        modifier = modifier,
    ) {
        items(count = images.size, itemContent = { index ->
            val selectColor = if (actualValue == index) ForestGreen else Platinum
            Image(
                painter = rememberImagePainter(images[index].getLinkPhotoGoogle(SIZE_60), builder = {
                    crossfade(true)
                }),
                contentDescription = null,
                modifier = Modifier
                    .size(size)
                    .padding(Small50)
                    .border(TwoDp, selectColor)
                    .clickable { onClickSelect(index) }
            )
        })
    }
}

@Preview
@Composable
fun ListImagesPreview() {
    CarouselImages(
        images = listOf(
            "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg",
            "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg",
            "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg",

        ),
        size = 50.dp,
        actualValue = 2,
    ) {
    }
}
