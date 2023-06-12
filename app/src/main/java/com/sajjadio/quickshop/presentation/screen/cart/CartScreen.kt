package com.sajjadio.quickshop.presentation.screen.cart

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.Tajawal
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor

@Composable
fun CartScreen(
    calculateBottomPadding: Dp,
    viewModel: CartViewModel = hiltViewModel()
) {
    CartContent(
        state = viewModel.state.value,
        calculateBottomPadding = calculateBottomPadding
    )
}

@Composable
fun CartContent(state: CartUiState, calculateBottomPadding: Dp) {
    SpacerVertical(height = 16)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.weight(1.5f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.carts) { cart ->
                CartItem(cart)
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

@Composable
private fun CartItem(cart: Cart) {
    Card(
        colors = CardDefaults.cardColors(containerColor = TextInputFiledColor),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxSize()

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage(painter = rememberAsyncImagePainter(model = cart.productImage))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                OrderAndDateRow(cart)
                CartText(text = cart.productCategory)
                CartText(text = if (cart.count > 1) "${cart.count} Items" else "${cart.count} Item")
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
            text = "${stringResource(id = R.string.order)} #${cart.order}",
            fontSize = 12,
            fontWeight = FontWeight.Medium
        )
        CartText(
            text = cart.date,
            fontSize = 10,
            fontWeight = FontWeight.SemiBold,
            color = SecondaryTextColor
        )
    }
}

@Composable
private fun PriceAndStateOfProductRow(cart: Cart) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CartText(
            text = "$${cart.productPrice}",
            fontSize = 14,
            fontWeight = FontWeight.SemiBold
        )
        CheckStatsOfProduct(cart)
    }
}

@Composable
private fun CheckStatsOfProduct(cart: Cart) {
    if (cart.isShipping) {
        StateOfProduct(cart, color = AccentColor, fontWeight = FontWeight.SemiBold)
    } else {
        StateOfProduct(cart, color = SecondaryTextColor, fontWeight = FontWeight.Normal)
    }
}

@Composable
private fun StateOfProduct(cart: Cart, color: Color, fontWeight: FontWeight) {
    CartText(
        text = cart.productState,
        fontWeight = fontWeight,
        color = color
    )
}


@Composable
fun CheckOutCart(
    cart: CartUiState,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = TextInputFiledColor),
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
                title = if (cart.count > 1) "Items" else "Item",
                value = cart.count.toString(),
                fontWeight = FontWeight.SemiBold
            )
            CheckOutRow(
                title = stringResource(id = R.string.shipping_fee),
                value = "$${cart.shippingFee}",
                fontWeight = FontWeight.SemiBold
            )
            CheckOutRow(
                title = stringResource(id = R.string.total),
                value = "$${cart.total}",
                fontWeight = FontWeight.SemiBold
            )
            CheckOutButton() {}
        }
    }
}

@Composable
private fun CheckOutRow(
    title: String,
    value: String,
    fontWeight: FontWeight,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CartText(text = title)
        CartText(text = value, fontWeight = fontWeight)
    }
}

@Composable
private fun CheckOutButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(AccentColor),
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() }
    ) {
        CartText(
            text = stringResource(id = R.string.check_out),
            fontSize = 14,
            fontWeight = FontWeight.SemiBold,
            color = BaseColor,
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
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )
    }
}

@Composable
private fun CartText(
    text: String,
    fontSize: Int = 12,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = PrimaryTextAndIconColor,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        fontFamily = Tajawal,
        fontSize = fontSize.sp,
        color = color,
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCartScreen() {
    CartScreen(0.dp)
}