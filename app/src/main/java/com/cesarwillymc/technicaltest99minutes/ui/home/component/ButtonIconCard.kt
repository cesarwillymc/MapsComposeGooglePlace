package com.cesarwillymc.technicaltest99minutes.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.cesarwillymc.technicaltest99minutes.ui.theme.NormalElevation
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small100
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small120
import com.cesarwillymc.technicaltest99minutes.ui.theme.White

/**
 * Created by Willy on 11/24/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Composable
fun ButtonIconCard(
    modifier: Modifier = Modifier,
    padding: Dp = Small120,
    resource: Int,
    background: Color = White,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = Small120, vertical = Small100),
        backgroundColor = background,
        elevation = NormalElevation
    ) {
        Image(
            painter = painterResource(id = resource),
            contentDescription = null,
            modifier = Modifier
                .clickable { onClick() }
                .padding(padding),
        )
    }
}
