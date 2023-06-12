package com.sajjadio.quickshop.presentation.screen.products

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "productsScreen"

fun NavController.navigateToProducts(){
    navigate(ROUTE)
}

fun NavGraphBuilder.productsRoute(navController: NavController){
    composable(ROUTE){
        ProductsScreen(navController = navController)
    }
}