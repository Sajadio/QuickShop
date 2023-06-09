package com.sajjadio.quickshop.presentation

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.Poppins
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Wishlist,
        Screen.Cart,
        Screen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Blue
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

@Composable
fun RowScope.BottomItem(
    screen: Screen,
    navController: NavHostController,
    currentNavDestination: NavDestination?
) {

    var selectedScreen by remember { mutableStateOf(Screen.Home.route) }


    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = Color.Black,
            unselectedTextColor = SecondaryTextColor,
            indicatorColor = Color(0xFFFFFFFF),
            unselectedIconColor = SecondaryTextColor,
            selectedIconColor = AccentColor
        ),
        alwaysShowLabel = false,
        label = {
            Text(
                text = stringResource(id = screen.title),
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
        },
        icon = {
            if (selectedScreen == screen.route) {
                BottomBarIcon(
                    screen.selectedIcon,
                    screen.title
                )
            } else {
                BottomBarIcon(
                    screen.icon,
                    screen.title
                )
            }
        },
        selected = currentNavDestination?.hierarchy?.any {
            selectedScreen = it.route.toString()
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
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
        contentDescription = stringResource(id = contentDescription),

        )
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    NavScreen()
}