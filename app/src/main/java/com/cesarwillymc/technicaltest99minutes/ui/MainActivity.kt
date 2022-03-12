package com.cesarwillymc.technicaltest99minutes.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.cesarwillymc.technicaltest99minutes.ui.route.HomeRoute
import com.cesarwillymc.technicaltest99minutes.ui.route.graph.GreenCrossNavGraph
import com.cesarwillymc.technicaltest99minutes.ui.theme.GreenCrossTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenCrossTheme {

                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    val systemUiController = rememberSystemUiController()

                    /* Side effects */
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            Color.Transparent,
                            darkIcons = true
                        )
                    }

                    val navController = rememberNavController()
                    Scaffold{ paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            GreenCrossNavGraph(
                                navController = navController,
                                startDestination = HomeRoute.Home.path,
                                onClickSettings = {
                                    startActivity(
                                        Intent(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", packageName, null)
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
