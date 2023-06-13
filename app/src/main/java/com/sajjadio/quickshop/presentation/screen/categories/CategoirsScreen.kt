package com.sajjadio.quickshop.presentation.screen.categories

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
import com.sajjadio.quickshop.presentation.components.CategoryItem
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import com.sajjadio.quickshop.presentation.screen.home.navigateToHomeScreen
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel(),
    navController: NavController
) {

    CategoriesContent(viewModel.stats.value) {
        navController.navigateToHomeScreen()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoriesContent(
    state: CategoryUiState,
    onClickBack: () -> Unit
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
                count = state.categories.size,
            ) {
                CategoryItem(
                    state = state.categories[it],
                    onClick = {},
                )
            }
        }
    }
}
