package com.sajjadio.quickshop.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sajjadio.quickshop.presentation.screen.cart.cartRoute
import com.sajjadio.quickshop.presentation.screen.cart.wishListRoute
import com.sajjadio.quickshop.presentation.screen.categories.categoriesRoute
import com.sajjadio.quickshop.presentation.screen.home.homeRoute
import com.sajjadio.quickshop.presentation.screen.product_details.productDetailsRoute
import com.sajjadio.quickshop.presentation.screen.products.productsRoute
import com.sajjadio.quickshop.presentation.screen.productsByCategory.productsByCategoryRoute
import com.sajjadio.quickshop.presentation.screen.profile.profileRoute
import com.sajjadio.quickshop.presentation.screen.search.searchRoute
import com.sajjadio.quickshop.presentation.screen.wishlist.WishListScreen

@ExperimentalPagerApi
@Composable
fun QuickShopNavGraph(
    navController: NavHostController,
    calculateBottomPadding: Dp,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        homeRoute(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController
        )
        composable(route = Screen.Wishlist.route) {
            WishListScreen(calculateBottomPadding, navController)
        }
        wishListRoute(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController
        )
        cartRoute(
            navController = navController,
            calculateBottomPadding = calculateBottomPadding
        )
        profileRoute(navController = navController)
        productsRoute(navController = navController)
        productDetailsRoute(navController = navController)
        categoriesRoute(navController = navController)
        productsByCategoryRoute(navController = navController)
        searchRoute(navController = navController)
    }
}