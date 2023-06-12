package com.sajjadio.quickshop.presentation.screen.cart

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sajjadio.quickshop.presentation.Screen
import com.sajjadio.quickshop.presentation.screen.wishlist.WishListScreen


fun NavGraphBuilder.wishListRoute(navController: NavController, calculateBottomPadding: Dp) {
    composable(Screen.Wishlist.route) {
        WishListScreen(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController,
        )
    }
}