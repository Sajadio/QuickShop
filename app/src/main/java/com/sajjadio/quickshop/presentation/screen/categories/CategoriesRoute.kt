package com.sajjadio.quickshop.presentation.screen.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "categoriesScreen"

fun NavController.navigateToCategories() {
    navigate(ROUTE)
}

fun NavGraphBuilder.categoriesRoute(navController: NavController) {
    composable(ROUTE) {
        CategoriesScreen(navController = navController)
    }
}