package com.sajjadio.quickshop.presentation.screen.cart

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.Poppins
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    CartContent(state = viewModel.state.value)
}

@Composable
fun CartContent(state: CartUiState) {
    SpacerVertical(height = 16)
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(state.carts) { cart ->
            CartItem(cart)
        }
    }
}

@Composable
private fun CartItem(cart: Cart) {
    Card(
        colors = CardDefaults.cardColors(containerColor = TextInputFiledColor),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
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
                        fontWeight = FontWeight.Normal,
                        color = SecondaryTextColor
                    )
                }
                CartText(
                    text = cart.productCategory,
                    fontSize = 10,
                    fontWeight = FontWeight.Normal
                )
                CartText(
                    text = "${cart.count} Items",
                    fontSize = 10,
                    fontWeight = FontWeight.Normal
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    CartText(
                        text = "$${cart.productPrice}",
                        fontSize = 18,
                        fontWeight = FontWeight.SemiBold
                    )
                    if (cart.isShipping) {
                        CartText(
                            text = cart.productState,
                            fontSize = 10,
                            fontWeight = FontWeight.SemiBold,
                            color = AccentColor
                        )
                    } else {
                        CartText(
                            text = cart.productState,
                            fontSize = 10,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryTextColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CartText(
    text: String,
    fontSize: Int,
    fontWeight: FontWeight,
    color: Color = PrimaryTextAndIconColor
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        fontFamily = Poppins,
        color = color
    )
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

@Preview(showSystemUi = true)
@Composable
fun PreviewCartScreen() {
    CartScreen()
}