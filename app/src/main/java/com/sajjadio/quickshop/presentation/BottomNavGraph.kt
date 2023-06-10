package com.sajjadio.quickshop.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sajjadio.quickshop.presentation.screen.cart.CartScreen
import com.sajjadio.quickshop.presentation.screen.home.HomeScreen
import com.sajjadio.quickshop.presentation.screen.profile.ProfileScreen
import com.sajjadio.quickshop.presentation.screen.wishlist.WishListScreen

@ExperimentalPagerApi
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    calculateBottomPadding: Dp,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(calculateBottomPadding)
        }
        composable(route = Screen.Wishlist.route) {
            WishListScreen()
        }
        composable(route = Screen.Cart.route) {
            CartScreen(calculateBottomPadding)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}