package com.sajjadio.quickshop.presentation.screen.products

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.AppBar
import com.sajjadio.quickshop.presentation.components.CircularProgressBar
import com.sajjadio.quickshop.presentation.components.ErrorBox
import com.sajjadio.quickshop.presentation.components.ProductItem
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import com.sajjadio.quickshop.presentation.screen.home.navigateToHomeScreen
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    ProductsContent(
        state,
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
        if (state.error.isNotEmpty()) {
            ErrorBox(text = state.error)
        }
        if (state.isLoading) {
            CircularProgressBar()
        }
        if (state.products != null) {
            ShowProduct(state, it)
        }
    }
}

@Composable
private fun ShowProduct(state: ProductUiState, paddingValues: PaddingValues) {
    LazyVerticalGrid(
        modifier = Modifier
            .background(BaseColor)
            .fillMaxSize()
            .padding(top = 8.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        state.products?.let {
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
