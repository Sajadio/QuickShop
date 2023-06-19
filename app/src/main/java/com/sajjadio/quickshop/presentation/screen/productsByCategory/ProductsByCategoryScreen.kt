package com.sajjadio.quickshop.presentation.screen.productsByCategory

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.AppBar
import com.sajjadio.quickshop.presentation.components.CheckUiState
import com.sajjadio.quickshop.presentation.components.ProductContainer
import com.sajjadio.quickshop.presentation.components.ProductItem
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import com.sajjadio.quickshop.presentation.screen.product_details.navigateToProductDetails
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor

@Composable
fun ProductsScreen(
    viewModel: ProductsByCategoryViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.uiState.collectAsState()

    ProductsContent(
        state = state,
        title = viewModel.title,
        onClickBack = navController::popBackStack,
        onClickAddToCart = {},
        onClickProductItem = navController::navigateToProductDetails
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductsContent(
    state: ProductUiState,
    title: String,
    onClickBack: () -> Unit,
    onClickAddToCart: (Int) -> Unit,
    onClickProductItem: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            Surface(
                elevation = 1.dp,
            ) {
                AppBar(
                    title = title,
                    painter = painterResource(id = R.drawable.ic_left_back),
                    onClickBack = onClickBack
                )
            }
        }
    ) { paddingValues ->
        CheckUiState(isLoading = state.isLoading, error = state.error, state.products) { products ->
            ProductContainer(
                products,
                onClickAddToCart = onClickAddToCart,
                onClickProductItem = onClickProductItem,
                paddingValues = paddingValues.calculateTopPadding()
            )
        }
    }

}
