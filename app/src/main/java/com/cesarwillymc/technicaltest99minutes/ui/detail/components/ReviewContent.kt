package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.PlaceReview
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal100
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack
import com.cesarwillymc.technicaltest99minutes.ui.theme.TextLarge

/**
 * Created by cesarwillymamanicanaza on 13/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Composable
fun ReviewContent(list: List<PlaceReview>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.til_recommend),
            style = MaterialTheme.typography.subtitle1.copy(fontSize = TextLarge),
            color = OnyxBlack,
            modifier = Modifier.padding(vertical = Normal100)
        )
        list.map {
            CardReviewItem(review = it)
        }
    }
}
