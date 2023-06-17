package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductContainer(
    state: ProductUiState,
    paddingValues: Dp,
    onClickAddToCart: (Int) -> Unit,
    onClickProductItem: (Int) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .background(BaseColor)
            .fillMaxSize()
            .padding(top = 8.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = paddingValues,
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        state.products?.let { products ->
            items(
                items = products,
                key = { product ->
                    product.title
                }
            ) { product ->
                ProductItem(
                    state = product,
                    onClickItem = onClickProductItem,
                    onClickAddToCart = onClickAddToCart,
                    modifier = Modifier.animateItemPlacement()
                )
            }
        }
    }
}
