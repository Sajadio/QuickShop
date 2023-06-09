package com.sajjadio.quickshop.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sajjadio.quickshop.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
) {
    object Home :
        Screen("homeScreen", R.string.home, R.drawable.ic_home_selected, R.drawable.ic_selected_home)

    object Wishlist :
        Screen("wishListScreen", R.string.wishlist, R.drawable.ic_fav, R.drawable.ic_selected_fav)

    object Cart :
        Screen("cartScreen", R.string.cart, R.drawable.ic_cart, R.drawable.ic_selected_cart)

    object Profile : Screen(
        "profileScreen",
        R.string.profile,
        R.drawable.ic_profile,
        R.drawable.ic_selected_profile
    )
}
