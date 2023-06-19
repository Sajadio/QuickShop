package com.sajjadio.quickshop.presentation.screen.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.sajjadio.quickshop.presentation.components.CategoryItem
import com.sajjadio.quickshop.presentation.components.CheckUiState
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import com.sajjadio.quickshop.presentation.screen.home.navigateToHomeScreen
import com.sajjadio.quickshop.presentation.screen.productsByCategory.navigateToProductsByCategory
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiStats.collectAsState()
    CategoriesContent(
        state,
        onClickBack = navController::navigateToHomeScreen,
        onClickCategoryItem = navController::navigateToProductsByCategory
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoriesContent(
    state: CategoryUiState,
    onClickBack: () -> Unit,
    onClickCategoryItem: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Surface(
                elevation = 1.dp,
            ) {
                AppBar(
                    title = "Categories",
                    painter = painterResource(id = R.drawable.ic_left),
                    onClickBack = { onClickBack() }
                )
            }
        }
    ) { paddingValues ->
        CheckUiState(
            isLoading = state.isLoading,
            error = state.error,
            state.categories
        ) { categories ->
            Categories(paddingValues, categories, onClickCategoryItem)
        }
    }
}

@Composable
private fun Categories(
    paddingValues: PaddingValues,
    categories: List<String>,
    onClickCategoryItem: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .background(BaseColor)
            .fillMaxSize()
            .padding(top = 8.dp),
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = categories,
            key = { category -> category }
        ) { category ->
            CategoryItem(category, onClickCategoryItem = onClickCategoryItem)
        }
    }
}
