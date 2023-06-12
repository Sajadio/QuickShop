package com.sajjadio.quickshop.presentation.screen.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.presentation.components.ProductItem
import com.sajjadio.quickshop.presentation.screen.home.ProductUiState

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
    navController: NavController
) {
    ProductsContent(viewModel.state.value)
}

@Composable
private fun ProductsContent(
    state: ProductUiState
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = state.products.size,
            key = {
                state.products
            }
        ) {
            ProductItem(
                state = state.products[it],
                onClick = {},
                onClickAddToCart = {}
            )
        }
    }
}
