package com.sajjadio.quickshop.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.ProductItem
import com.sajjadio.quickshop.presentation.components.StaticIcon
import com.sajjadio.quickshop.presentation.screen.common.Product
import com.sajjadio.quickshop.presentation.screen.product_details.navigateToProductDetails
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import com.sajjadio.quickshop.presentation.ui.theme.Typography

@Composable
fun SearchScreen(
    viewModel: SearchVewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state
    val query by viewModel.searchQuery
    SearchContent(
        state = state.products,
        query = query,
        onTextChange = { viewModel.setSearchQuery(it) }
    ) { navController.navigateToProductDetails(it) }
}

@Composable
private fun SearchContent(
    state: List<Product>,
    query: String,
    onTextChange: (String) -> Unit,
    onClickProductItem: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBox(query = query, onTextChange = onTextChange)
        LazyVerticalGrid(
            modifier = Modifier
                .background(BaseColor)
                .fillMaxSize()
                .padding(top = 8.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            items(
                items = state,
              ) {
                ProductItem(
                    state = it,
                    onClickItem = { onClickProductItem(it) },
                    onClickAddToCart = {}
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBox(
    query: String,
    onTextChange: (String) -> Unit
) {
    Box(modifier = Modifier.padding(top = 8.dp)) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                value = query,
                onValueChange = { onTextChange(it) },
                leadingIcon = {
                    StaticIcon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = stringResource(id = R.string.search),
                        tint = SecondaryTextColor
                    )
                },
                textStyle = Typography.bodyMedium,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = PrimaryTextAndIconColor,
                    containerColor = TextInputFiledColor,
                    cursorColor = AccentColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                label = {
                    Body(title = stringResource(id = R.string.search), color = SecondaryTextColor)
                },
            )
        }
    }
}
