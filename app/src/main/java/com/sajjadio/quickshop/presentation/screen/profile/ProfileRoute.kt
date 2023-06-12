package com.sajjadio.quickshop.presentation.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sajjadio.quickshop.presentation.Screen


fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(Screen.Profile.route) {
        ProfileScreen(navController = navController)
    }
}