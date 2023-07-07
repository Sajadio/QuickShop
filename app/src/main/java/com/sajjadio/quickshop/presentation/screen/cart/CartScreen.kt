package com.sajjadio.quickshop.presentation.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.domain.model.cart.Cart
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.CheckUiState
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.screen.product_details.navigateToProductDetails
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.CardBackgroundColor
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.utils.formattedDate

@Composable
fun CartScreen(
    calculateBottomPadding: Dp,
    viewModel: CartViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.uiState.collectAsState()

    CartContent(
        state = state,
        calculateBottomPadding = calculateBottomPadding,
        onClickItem = { navController.navigateToProductDetails(it) }
    )
}

@Composable
fun CartContent(
    state: CartsUiState,
    calculateBottomPadding: Dp,
    onClickItem: (Int) -> Unit
) {

    SpacerVertical(height = 16)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CheckUiState(
            isLoading = state.isLoading,
            error = state.error,
            data = state.carts
        ) { carts ->
            LazyColumn(
                modifier = Modifier.weight(1.7f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(carts) { cart ->
                    CartItem(cart, onClickItem = onClickItem)
                }
            }
            CheckOutCart(
                cart = state,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        bottom = calculateBottomPadding,
                        start = 8.dp,
                        end = 8.dp,
                    )
            )
        }
    }
}

@Composable
private fun CartItem(
    cart: Cart,
    onClickItem: (Int) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClickItem(cart.id) }

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage(painter = rememberAsyncImagePainter(model = cart.cartProduct.image))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                OrderAndDateRow(cart)
                CartText(title = cart.cartProduct.category)
                CartText(title = if (cart.cartProduct.quantity > 1) "${cart.cartProduct.quantity} Items" else "${cart.cartProduct.quantity} Item")
                PriceAndStateOfProductRow(cart)
            }
        }
    }
}

@Composable
private fun OrderAndDateRow(cart: Cart) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CartText(
            title = "${stringResource(id = R.string.order)} #${1212}",
            style = AppTypography.labelLarge
        )
        cart.date.formattedDate()?.let {
            CartText(
                title = it,
                color = SecondaryTextColor
            )
        }
    }
}

@Composable
private fun PriceAndStateOfProductRow(cart: Cart) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CartText(
            title = "$${cart.cartProduct.price}",
            style = AppTypography.labelLarge
        )
        CheckStatsOfProduct(cart)
    }
}

@Composable
private fun CheckStatsOfProduct(cart: Cart) {
    if (cart.isShipping) {
        StateOfProduct(
            cart, color = AccentColor, style = AppTypography.labelLarge
        )
    } else {
        StateOfProduct(cart, color = SecondaryTextColor, style = AppTypography.labelSmall)
    }
}

@Composable
private fun StateOfProduct(cart: Cart, color: Color, style: TextStyle) {
    CartText(
        title = cart.productState,
        color = color,
        style = style
    )
}


@Composable
fun CheckOutCart(
    cart: CartsUiState,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CheckOutRow(
                title = if (cart.carts?.size!! > 1) "Items" else "Item",
                value = cart.carts.size.toString(),
            )
            CheckOutRow(
                title = stringResource(id = R.string.shipping_fee),
                value = "$60.00",
            )
            CheckOutRow(
                title = stringResource(id = R.string.total),
                value = "$2248.04",
            )
            CheckOutButton() {}
        }
    }
}

@Composable
private fun CheckOutRow(
    title: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CartText(title = title)
        CartText(title = value, style = AppTypography.labelLarge)
    }
}

@Composable
private fun CheckOutButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = ButtonDefaults.buttonColors(AccentColor),
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() }
    ) {
        CartText(
            title = stringResource(id = R.string.check_out),
            color = BaseColor,
            style = AppTypography.bodyMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
private fun ProductImage(painter: Painter) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .wrapContentSize()
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter, contentDescription = "",
            modifier = Modifier.fillMaxSize().background(BaseColor),
        )
    }
}

@Composable
private fun CartText(
    title: String,
    color: Color = PrimaryTextAndIconColor,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTypography.labelSmall,
) {
    Body(
        title = title,
        style = style,
        color = color,
        modifier = modifier,
    )
}
