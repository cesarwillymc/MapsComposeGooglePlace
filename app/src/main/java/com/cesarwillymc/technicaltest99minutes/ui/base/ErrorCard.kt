package com.cesarwillymc.technicaltest99minutes.ui.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.ui.theme.ForestGreen
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal100
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal150
import com.cesarwillymc.technicaltest99minutes.ui.theme.OneDp
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack

/**
 * Created by cesarwillymamanicanaza on 26/01/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Composable
fun ErrorCard(modifier: Modifier = Modifier, error: String, onClickRetry: () -> Unit) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(
                Alignment.Center
            )
        ) {
            Image(painter = painterResource(id = R.drawable.ic_alert), contentDescription = null)
            Text(
                text = error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = Normal100)
                    .padding(horizontal = Normal150)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.body1,
                color = OnyxBlack
            )

            TextButton(onClick = onClickRetry, border = BorderStroke(OneDp, ForestGreen)) {
                Text(
                    stringResource(R.string.til_try_again),
                    style = MaterialTheme.typography.h2,
                    color = ForestGreen,
                )
            }
        }
    }
}
