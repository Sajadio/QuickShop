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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.screen.home.Product
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.Poppins
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor

@Composable
fun ProductItem(
    state: Product,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    onClickAddToCart: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .clickable { onClick(state.id) },
        colors = CardDefaults.cardColors(BaseColor),
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
        ProductTitle(state)
        SpacerVertical(height = 4)
        CategoryTitle(state)
        SpacerVertical(height = 4)
        ContainerRating(state)
        SpacerVertical(height = 8)
        ContainerPriceAndButtonCart(state, onClickAddToCart)
    }
}


@Composable
private fun ProductImage(
    state: Product
) {
    Image(
        painter = rememberAsyncImagePainter(model = state.poster),
        contentDescription = state.title,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.FillHeight
    )
}

@Composable
private fun ProductTitle(state: Product) {
    Text(
        text = state.title,
        fontSize = 16.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        maxLines = 1,
        color = PrimaryTextAndIconColor
    )
}

@Composable
private fun CategoryTitle(state: Product) {
    Text(
        text = state.category,
        fontSize = 14.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        maxLines = 1,
        color = PrimaryTextAndIconColor
    )
}

@Composable
private fun ContainerRating(state: Product) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingBar(state.rate.toInt())
        TextRatingBar(state)
    }
}


@Composable
private fun RatingBar(
    rating: Int,
    maxRating: Int = 5
) {
    val outlineRating = maxRating - rating
    Row(modifier = Modifier.padding(end = 8.dp)) {
        AddRatingBar(
            rating = rating,
            painter = painterResource(id = R.drawable.ic_fill_star),
            description = "Filled star",
            tint = SecondaryColor
        )
        AddRatingBar(
            rating = outlineRating,
            painter = painterResource(id = R.drawable.ic_outline_star),
            description = "Outlined star ",
            tint = SecondaryTextColor
        )
    }
}

@Composable
private fun AddRatingBar(
    rating: Int,
    painter: Painter,
    description: String,
    tint: Color
) {
    repeat(rating) {
        StaticIcon(
            painter = painter,
            contentDescription = description,
            tint = tint
        )
    }
}

@Composable
private fun TextRatingBar(state: Product) {
    Text(
        text = state.rate.toString(),
        fontSize = 12.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        maxLines = 1,
        color = SecondaryTextColor
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
        Price(state)
        ButtonCart(onClickAddToCart, state)
    }
}

@Composable
private fun Price(state: Product) {
    Text(
        text = "$${state.price}",
        fontSize = 18.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        maxLines = 1,
        color = PrimaryTextAndIconColor
    )
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
