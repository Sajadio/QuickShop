package com.sajjadio.quickshop.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sajjadio.quickshop.presentation.ui.theme.QuickShopTheme

class QuickShopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickShopTheme {
                NavScreen()
            }
        }
    }
}
