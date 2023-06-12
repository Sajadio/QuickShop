package com.sajjadio.quickshop.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sajjadio.quickshop.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val unSelectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) {
    object Home : Screen(
            route = "homeScreen",
            title = R.string.home,
            unSelectedIcon = R.drawable.ic_unselected_home,
            selectedIcon = R.drawable.ic_selected_home
        )

    object Wishlist : Screen(
            route = "wishListScreen",
            title = R.string.wishlist,
            unSelectedIcon = R.drawable.ic_unselected_fav,
            selectedIcon = R.drawable.ic_selected_fav
        )

    object Cart : Screen(
            route = "cartScreen",
            title = R.string.cart,
            unSelectedIcon = R.drawable.ic_unselected_cart,
            selectedIcon = R.drawable.ic_selected_cart
        )

    object Profile : Screen(
        route = "profileScreen",
        title = R.string.profile,
        unSelectedIcon = R.drawable.ic_unselected_profile,
        selectedIcon = R.drawable.ic_selected_profile
    )
}
