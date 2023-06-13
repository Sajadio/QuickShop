package com.sajjadio.quickshop.presentation.screen.product_details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ROUTE = "productDetailsScreen"

fun NavController.navigateToProductDetails(id: Int) {
    navigate("${ROUTE}/$id")
}

fun NavGraphBuilder.productDetailsRoute(navController: NavController) {
    composable(
        "$ROUTE/{${ProductDetailsArgs.NAME_ARG}}",
        arguments = listOf(
            navArgument(ProductDetailsArgs.NAME_ARG) {
                NavType.IntType
            }
        )
    ) {
        ProductDetailsScreen(navController = navController)
    }
}

class ProductDetailsArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[NAME_ARG]).toString().toInt()

    companion object {
        const val NAME_ARG = "id"
    }
}