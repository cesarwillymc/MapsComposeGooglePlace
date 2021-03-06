package com.cesarwillymc.technicaltest99minutes.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.ui.base.WithoutInternetCard
import com.cesarwillymc.technicaltest99minutes.ui.route.HomeRoute
import com.cesarwillymc.technicaltest99minutes.ui.route.graph.GreenCrossNavGraph
import com.cesarwillymc.technicaltest99minutes.ui.theme.GreenCrossTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressWarnings("LongMethod")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenCrossTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    val systemUiController = rememberSystemUiController()
                    val mainViewModel: MainViewModel = hiltViewModel()
                    val hasInternet by mainViewModel.hasInternet.collectAsState()

                    /* Side effects */
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            Color.Transparent,
                            darkIcons = true
                        )
                    }

                    val navController = rememberNavController()
                    Scaffold { paddingValues ->
                        ConstraintLayout(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxSize()
                        ) {
                            val (body, error) = createRefs()
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .constrainAs(body) {
                                        top.linkTo(parent.top)
                                        bottom.linkTo(if (hasInternet) parent.bottom else error.top)
                                        height = Dimension.fillToConstraints
                                    }
                            ) {
                                GreenCrossNavGraph(
                                    navController = navController,
                                    startDestination = HomeRoute.Home.path,
                                    onClickSettings = {
                                        startActivity(
                                            Intent(
                                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts(
                                                    getString(R.string.til_package),
                                                    packageName,
                                                    null
                                                )
                                            )
                                        )
                                    }
                                )
                            }
                            if (!hasInternet) {
                                WithoutInternetCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .constrainAs(error) {
                                            bottom.linkTo(parent.bottom)
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
