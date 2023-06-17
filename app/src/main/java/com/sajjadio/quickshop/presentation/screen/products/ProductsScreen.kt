package com.sajjadio.quickshop.presentation.screen.products

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import com.sajjadio.quickshop.presentation.screen.home.navigateToHomeScreen
import com.sajjadio.quickshop.presentation.screen.product_details.navigateToProductDetails

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    ProductsContent(
        state,
        onClickBack = {
            navController.navigateToHomeScreen()
        },
        onClickAddToCart = {},
        onClickProductItem = { navController.navigateToProductDetails(it) }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductsContent(
    state: ProductUiState,
    onClickBack: () -> Unit,
    onClickAddToCart: (Int) -> Unit,
    onClickProductItem: (Int) -> Unit,
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
    ) {paddingValues ->
        Column(modifier = Modifier.fillMaxSize()) {
            CheckUiState(isLoading = state.isLoading, error = state.error) {
                if (it){
                    ProductContainer(
                        state,
                        onClickAddToCart = onClickAddToCart,
                        onClickProductItem = onClickProductItem,
                        paddingValues = paddingValues.calculateTopPadding()
                    )
                }
            }
        }
    }
}
