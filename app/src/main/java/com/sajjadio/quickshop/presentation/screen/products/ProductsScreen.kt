package com.sajjadio.quickshop.presentation.screen.products

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.AppBar
import com.sajjadio.quickshop.presentation.components.ProductItem
import com.sajjadio.quickshop.presentation.screen.home.ProductUiState
import com.sajjadio.quickshop.presentation.screen.home.navigateToHomeScreen
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
    navController: NavController
) {
    ProductsContent(
        viewModel.state.value,
        onClickBack = {
            navController.navigateToHomeScreen()
        })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductsContent(
    state: ProductUiState,
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            Surface(
                elevation = 1.dp,
            ) {
                AppBar(
                    title = "Products",
                    painter = painterResource(id = R.drawable.ic_left),
                    onClickBack = { onClickBack() }
                )
            }
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .background(BaseColor)
                .fillMaxSize()
                .padding(top = 8.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                top = it.calculateTopPadding(),
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                count = state.products.size,
            ) {
                ProductItem(
                    state = state.products[it],
                    onClickItem = {},
                    onClickAddToCart = {}
                )
            }
        }
    }

}
