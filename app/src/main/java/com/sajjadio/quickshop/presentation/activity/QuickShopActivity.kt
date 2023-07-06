package com.sajjadio.quickshop.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sajjadio.quickshop.presentation.BottomNavScreen
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.QuickShopTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class QuickShopActivity : ComponentActivity() {

    private val viewModel:SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.iaLoading.value
            }
        }
        setContent {
            QuickShopTheme {
                ChangeStatusBarColor()
                BottomNavScreen()
            }
        }
    }
}

@Composable
@Stable
private fun ChangeStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val isDark = isSystemInDarkTheme()
    LaunchedEffect(key1 = true) {
        if (isDark) {
            systemUiController.setStatusBarColor(color = Color.Gray, darkIcons = true)
        } else {
            systemUiController.setStatusBarColor(color = BaseColor, darkIcons = true)
        }
    }
}