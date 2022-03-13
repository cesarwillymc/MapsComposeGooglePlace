package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.extension.ZERO
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal100
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack
import com.cesarwillymc.technicaltest99minutes.ui.theme.Platinum
import com.cesarwillymc.technicaltest99minutes.ui.theme.TextLarge
import com.cesarwillymc.technicaltest99minutes.ui.theme.ThreeDp

/**
 * Created by cesarwillymamanicanaza on 13/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@ExperimentalComposeUiApi
@Composable
fun InformationPlaceContent(detailPlace: DetailPlace) {
    var selectImage by rememberSaveable { mutableStateOf(ZERO) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Normal100)
    ) {
        if (!detailPlace.photos.isNullOrEmpty())
            GroupImagesContent(
                images = detailPlace.photos,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Normal100),
                selectImageIndex = selectImage,
                onClickSelect = { numberSelected ->
                    selectImage = numberSelected
                }
            )

        Text(
            text = detailPlace.name,
            style = MaterialTheme.typography.subtitle1.copy(fontSize = TextLarge),
            color = OnyxBlack,
            modifier = Modifier.padding(vertical = Normal100)
        )

        Divider(color = Platinum, modifier = Modifier.padding(bottom = ThreeDp))

        TabCard(
            title = detailPlace.contactNumber,
            drawable = painterResource(R.drawable.ic_phone),
            onClick = {
                // Without action
            }
        )

        Divider(color = Platinum, modifier = Modifier.padding(bottom = ThreeDp))

        TabCard(
            title = detailPlace.address,
            drawable = painterResource(R.drawable.ic_pin),
            onClick = {
                // Without action
            }
        )
    }
}
