package com.sajjadio.quickshop.presentation.screen.wishlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController

@Composable
fun WishListScreen(calculateBottomPadding: Dp, navController: NavController) {
    WishListContent(

    )
}

@Composable
fun WishListContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "WishList Screen")
    }
}
