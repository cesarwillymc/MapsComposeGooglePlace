package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import com.cesarwillymc.technicaltest99minutes.ui.theme.ForestGreen
import com.cesarwillymc.technicaltest99minutes.ui.theme.Gray
import com.cesarwillymc.technicaltest99minutes.ui.theme.Honeydew
import com.cesarwillymc.technicaltest99minutes.ui.theme.ImageSuperMini
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal125
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack
import com.cesarwillymc.technicaltest99minutes.ui.theme.Silver
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small120

/**
 * Created by Willy on 11/18/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

@Composable
fun TabCard(
    title: String,
    drawable: Painter,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier.clickable(enabled) { onClick() }.fillMaxWidth(),
    ) {
        val (card, text, icon) = createRefs()
        Card(
            backgroundColor = Honeydew,
            modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(text.top)
                    bottom.linkTo(text.bottom)
                    start.linkTo(parent.start, margin = Small120)
                }
                .size(ImageSuperMini)
        ) {
            Icon(
                modifier = Modifier.padding(Small120),
                painter = drawable,
                contentDescription = null,
                tint = if (enabled) ForestGreen else Silver,
            )
        }
        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = Normal125)
                bottom.linkTo(parent.bottom, margin = Normal125)
                start.linkTo(card.end, margin = Small120)
                end.linkTo(icon.start, margin = Small120)
                width = fillToConstraints
            },
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = if (enabled) OnyxBlack else Gray,
            textAlign = TextAlign.Start
        )
        Icon(
            Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = Gray,
            modifier = Modifier.constrainAs(icon) {
                top.linkTo(text.top)
                bottom.linkTo(text.bottom)
                end.linkTo(parent.end, margin = Small120)
            }.size(Small120)

        )
    }
}
