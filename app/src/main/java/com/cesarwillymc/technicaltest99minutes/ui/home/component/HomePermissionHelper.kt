package com.cesarwillymc.technicaltest99minutes.ui.home.component

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HomePermissionHelper(
    permission: String,
    onGranted: () -> Unit,
    onDenied: () -> Unit,
    showPermissionPopup: Boolean = false
) {

    /* permission launcher */
    val launcherPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            onGranted()
        } else onDenied()
    }

    LaunchedEffect(showPermissionPopup) {
        if (showPermissionPopup)
            launcherPermission.launch(permission)
    }
}
