package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import com.sajjadio.quickshop.presentation.ui.theme.Typography

@Composable
fun ProductItem(
    state: Product = Product(),
    modifier: Modifier = Modifier,
    onClickItem: (Int) -> Unit,
    onClickAddToCart: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .clickable { onClickItem(state.id) },
        colors = CardDefaults.cardColors(TextInputFiledColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        CardContent(
            state = state,
            onClickAddToCart = onClickAddToCart
        )
    }
}

@Composable
private fun CardContent(
    state: Product,
    onClickAddToCart: (Int) -> Unit
) {
    ProductImage(state = state)
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        SpacerVertical(height = 8)
        Title(title = state.title, style = Typography.titleLarge)
        Body(title = state.category, style = Typography.bodyMedium)
        SpacerVertical(height = 4)
        state.rating?.rate?.let { ContainerRating(it) }
        SpacerVertical(height = 8)
        ContainerPriceAndButtonCart(state, onClickAddToCart)
    }
}


@Composable
private fun ProductImage(
    state: Product
) {
    Image(
        painter = rememberAsyncImagePainter(model = state.image),
        contentDescription = state.title,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.FillBounds
    )
}


@Composable
private fun ContainerPriceAndButtonCart(
    state: Product,
    onClickAddToCart: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Title(title = "$${state.price}", style = Typography.titleLarge)
        ButtonCart(onClickAddToCart, state)
    }
}

@Composable
private fun ButtonCart(
    onClickAddToCart: (Int) -> Unit,
    state: Product
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickAddToCart(state.id) }
            .size(48.dp)
            .background(AccentColor)
            .padding(12.dp)
    ) {
        StaticIcon(
            painter = painterResource(id = R.drawable.ic_unselected_cart),
            contentDescription = "Add item to cart",
            tint = BaseColor
        )
    }
}
