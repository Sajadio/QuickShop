package com.sajjadio.quickshop.presentation

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.Tajawal
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor

@ExperimentalPagerApi
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val visibility = currentRoute(navController = navController) in listOf(
                Screen.Home.route,
                Screen.Wishlist.route,
                Screen.Cart.route,
                Screen.Profile.route
            )
            BottomBar(navController = navController, visibility = visibility)
        },
        contentWindowInsets = WindowInsets.safeContent
    ) {
        QuickShopNavGraph(navController = navController, it.calculateBottomPadding())
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun BottomBar(navController: NavHostController, visibility: Boolean) {
    val screens = listOf(
        Screen.Home,
        Screen.Wishlist,
        Screen.Cart,
        Screen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (visibility) {
        Surface(
            elevation = 8.dp,
        ) {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Blue,
            ) {
                screens.forEach {
                    BottomItem(
                        screen = it,
                        navController = navController,
                        currentNavDestination = currentDestination
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.BottomItem(
    screen: Screen,
    navController: NavHostController,
    currentNavDestination: NavDestination?
) {
    val selected = currentNavDestination?.hierarchy?.any { it.route == screen.route } == true

    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = Color.Black,
            unselectedTextColor = SecondaryTextColor,
            indicatorColor = Color(0xFFFFFFFF),
            unselectedIconColor = SecondaryTextColor,
            selectedIconColor = AccentColor
        ),
        alwaysShowLabel = true,
        label = {
            Text(
                text = stringResource(id = screen.title),
                fontWeight = FontWeight.Bold,
                fontFamily = Tajawal
            )
        },
        icon = {
            if (selected) {
                BottomBarIcon(
                    screen.selectedIcon,
                    screen.title
                )
            } else {
                BottomBarIcon(
                    screen.unSelectedIcon,
                    screen.title
                )
            }
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

@Composable
fun BottomBarIcon(
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = stringResource(id = contentDescription)
    )
}

@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    NavScreen()
}