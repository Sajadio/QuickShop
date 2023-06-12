package com.sajjadio.quickshop.presentation.screen.cart

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sajjadio.quickshop.presentation.Screen


fun NavGraphBuilder.cartRoute(navController: NavController, calculateBottomPadding: Dp) {
    composable(Screen.Cart.route) {
        CartScreen(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController,
        )
    }
}