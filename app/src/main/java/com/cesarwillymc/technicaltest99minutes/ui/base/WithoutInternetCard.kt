package com.cesarwillymc.technicaltest99minutes.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.ui.theme.DangerRedLight
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal100
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small100
import com.cesarwillymc.technicaltest99minutes.ui.theme.White

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Composable
fun WithoutInternetCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(DangerRedLight)) {
        Row(
            Modifier
                .align(Alignment.Center)
                .padding(vertical = Normal100),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_alert),
                contentDescription = null,
                modifier = Modifier.padding(end = Small100)
            )
            Text(
                text = stringResource(R.string.lbl_without_internet),
                style = MaterialTheme.typography.h4,
                color = White
            )
        }
    }
}

@Preview
@Composable
fun PreviewCard() {
    WithoutInternetCard(Modifier.fillMaxWidth())
}
