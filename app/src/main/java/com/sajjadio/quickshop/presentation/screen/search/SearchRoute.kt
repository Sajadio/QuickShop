package com.sajjadio.quickshop.presentation.screen.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "searchScreen"
fun NavController.navigateToSearchScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.searchRoute(navController: NavController) {
    composable(ROUTE) {
        SearchScreen(navController = navController)
    }
}