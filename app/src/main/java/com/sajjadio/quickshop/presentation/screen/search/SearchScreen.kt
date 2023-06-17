package com.sajjadio.quickshop.presentation.screen.search


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.sajjadio.quickshop.presentation.components.CircularProgressBar
import com.sajjadio.quickshop.presentation.components.ErrorBox
import com.sajjadio.quickshop.presentation.components.ProductContainer
import com.sajjadio.quickshop.presentation.components.StaticIcon
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import com.sajjadio.quickshop.presentation.screen.product_details.navigateToProductDetails
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import com.sajjadio.quickshop.presentation.ui.theme.Typography

@Composable
fun SearchScreen(
    viewModel: SearchVewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val query by viewModel.searchQuery
    SearchContent(
        state = state,
        query = query,
        onTextChange = viewModel::setSearchQuery,
        onClickAddToCart = {}
    ) { navController.navigateToProductDetails(it) }
}

@Composable
private fun SearchContent(
    state: ProductUiState,
    query: String,
    onTextChange: (String) -> Unit,
    onClickAddToCart: (Int) -> Unit,
    onClickProductItem: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBox(query = query, onTextChange = onTextChange)

        if (state.error.isNotEmpty()) {
            ErrorBox(text = state.error)
        }
        if (state.isLoading) {
            CircularProgressBar()
        }
        if (state.products != null) {
            ProductContainer(
                state,
                onClickAddToCart = onClickAddToCart,
                onClickProductItem = onClickProductItem
            )
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
