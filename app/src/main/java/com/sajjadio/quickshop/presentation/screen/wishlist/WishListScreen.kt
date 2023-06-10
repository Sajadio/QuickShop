package com.sajjadio.quickshop.presentation.screen.wishlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WishListScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    WishListContent(
        state = viewModel.state.value
    )
}

@Composable
fun WishListContent(state: CartUiState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "WishList Screen")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewWishListScreen() {
    WishListScreen()
}