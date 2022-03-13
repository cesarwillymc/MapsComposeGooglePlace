package com.cesarwillymc.technicaltest99minutes.ui.base

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.cesarwillymc.technicaltest99minutes.extension.EMPTY_STRING
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack

@Composable
fun GreenCrossSimpleScaffold(
    modifier: Modifier = Modifier,
    toolbarTitle: String = EMPTY_STRING,
    navigateUp: () -> Unit,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    content: @Composable () -> Unit
) {
    Scaffold(modifier = modifier, topBar = {
        GreenCrossTopAppBar(
            title = {
                Text(
                    text = toolbarTitle,
                    style = MaterialTheme.typography.h2,
                    color = OnyxBlack
                )
            },
            navigationIcon = {
                GreenHomeUpButton(onNavigateUp = navigateUp)
            },
            elevation = elevation
        )
    }) {
        content()
    }
}
