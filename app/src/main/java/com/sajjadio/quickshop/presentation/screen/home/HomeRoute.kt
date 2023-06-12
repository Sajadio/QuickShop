package com.sajjadio.quickshop.presentation.screen.home

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sajjadio.quickshop.presentation.Screen

fun NavController.navigateToHomeScreen() {
    popBackStack(Screen.Home.route, true)
    navigate(Screen.Home.route)
}

@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.homeRoute(
    calculateBottomPadding: Dp,
    navController: NavController
) {
    composable(Screen.Home.route) {
        HomeScreen(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController
        )
    }
}