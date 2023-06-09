package com.sajjadio.quickshop.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.sajjadio.quickshop.presentation.ui.theme.QuickShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuickShopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickShopTheme {
                ChangeStatusBarColor()
                NavScreen()
            }
        }
    }
}

@Composable
private fun ChangeStatusBarColor() {
//    val systemUiController = rememberSystemUiController()
//    val useDarkIcons = !isSystemInDarkTheme()
//        systemUiController.setStatusBarColor(BaseColor, true)
//        systemUiController.setNavigationBarColor(Color.Black)
}