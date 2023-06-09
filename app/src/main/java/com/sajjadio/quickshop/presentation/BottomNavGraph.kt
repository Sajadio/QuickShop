package com.sajjadio.quickshop.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sajjadio.quickshop.presentation.screen.cart.CartScreen
import com.sajjadio.quickshop.presentation.screen.home.HomeScreen
import com.sajjadio.quickshop.presentation.screen.profile.ProfileScreen
import com.sajjadio.quickshop.presentation.screen.wishlist.WishListScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Wishlist.route) {
            WishListScreen()
        }
        composable(route = Screen.Cart.route) {
            CartScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}