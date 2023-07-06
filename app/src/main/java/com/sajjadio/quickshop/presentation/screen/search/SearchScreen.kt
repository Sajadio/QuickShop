package com.sajjadio.quickshop.presentation.screen.search


import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.CheckUiState
import com.sajjadio.quickshop.presentation.components.ProductContainer
import com.sajjadio.quickshop.presentation.components.StaticIcon
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import com.sajjadio.quickshop.presentation.screen.product_details.navigateToProductDetails
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography

@Composable
fun SearchScreen(
    viewModel: SearchVewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    val query by viewModel.searchQuery

    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val view = LocalView.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        showKeyboard(context, view)
    }

    SearchContent(
        state = state,
        query = query,
        focusRequester = focusRequester,
        onTextChange = viewModel::setSearchQuery,
        onClickAddToCart = {},
    ) { navController.navigateToProductDetails(it) }
}

@Composable
private fun SearchContent(
    state: ProductUiState,
    query: String,
    focusRequester: FocusRequester,
    onTextChange: (String) -> Unit,
    onClickAddToCart: (Int) -> Unit,
    onClickProductItem: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().focusRequester(focusRequester)) {
        SearchBox(query = query, onTextChange = onTextChange)

        CheckUiState(isLoading = state.isLoading, error = state.error, state.products) { products ->
            ProductContainer(
                products,
                onClickAddToCart = onClickAddToCart,
                onClickProductItem = onClickProductItem,
                paddingValues = 0.dp
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
                textStyle = AppTypography.bodyMedium,
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

@SuppressLint("ServiceCast")
private fun showKeyboard(context: Context, view: View?) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}