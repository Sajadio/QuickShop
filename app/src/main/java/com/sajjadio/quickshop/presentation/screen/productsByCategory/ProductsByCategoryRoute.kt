package com.sajjadio.quickshop.presentation.screen.productsByCategory

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "productsByCategoryScreen"

fun NavController.navigateToProductsByCategory(categoryName: String) {
    navigate("${ROUTE}/$categoryName")
}

fun NavGraphBuilder.productsByCategoryRoute(navController: NavController) {
    composable(
        "$ROUTE/{${ProductsByCategory.NAME_ARG }}",
        arguments = listOf(
            navArgument(ProductsByCategory.NAME_ARG) {
                NavType.StringType
            }
        )
    ) {
        ProductsScreen(navController = navController)
    }
}

class ProductsByCategory(savedStateHandle: SavedStateHandle) {
    val categoryName = checkNotNull(savedStateHandle[NAME_ARG])

    companion object {
        const val NAME_ARG = "category_name"
    }
}