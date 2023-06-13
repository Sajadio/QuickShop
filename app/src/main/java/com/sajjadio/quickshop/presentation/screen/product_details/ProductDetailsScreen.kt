package com.sajjadio.quickshop.presentation.screen.product_details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.ContainerRating
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.components.Title
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.LightWhiteColor
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.Typography

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavController,
) {
    ProductDetailsContent(
        itemCount = viewModel.itemCount
    ) { navController.popBackStack() }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ProductDetailsContent(
    itemCount: MutableState<Int>,
    onClickBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Box(modifier = Modifier.height(400.dp)) {
                ProductImage()
                BackButton(onClickBack)
            }
            ProductDetails()
        }
        Surface(
            elevation = 8.dp,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp),
                backgroundColor = Color.White,
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AddItemToCart(itemCount)
                    ContainerClickableButtons(itemCount)
                }
            }
        }
    }
}

@Composable
private fun ProductImage() {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = R.drawable.details_image),
        contentDescription = "product image",
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.TopCenter,
    )
}

@Composable
private fun BackButton(onClickBack: () -> Unit) {
    Box(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(LightWhiteColor)
                .clickable { onClickBack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_left),
                contentDescription = "Previous screen"
            )
        }
    }
}

@Composable
private fun ProductDetails() {
    Column(modifier = Modifier.padding(16.dp)) {
        Title(
            title = "Fjallraven  Foldsack No. 1 Backpack, Fits 15 Laptops",
            style = Typography.titleLarge,
            maxLine = 2
        )
        Body(
            title = "women's clothing",
            style = Typography.bodySmall,
            maxLine = 2
        )
        ContainerRating(4.7)
        Body(
            title = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            style = Typography.bodySmall,
            maxLine = 20
        )
        SpacerVertical(height = 16)
        Title(title = "$109.95", style = Typography.titleLarge)
    }
}

@Composable
private fun AddItemToCart(itemCount: MutableState<Int>) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(AccentColor)
            .clickable { itemCount.value = 1 }
            .fillMaxHeight()
            .width(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cart_white),
                contentDescription = "Add item to cart",
                tint = Color.White
            )
            Title(
                title = stringResource(id = R.string.add),
                color = Color.White,
                style = Typography.titleLarge
            )
        }
    }
}

@Composable
private fun ContainerClickableButtons(itemCount: MutableState<Int>) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ClickableButton("-", itemCount = itemCount.value) {
            if (it > 0)
                itemCount.value--
        }
        Title(title = itemCount.value.toString(), style = Typography.titleLarge)
        ClickableButton("+", itemCount = itemCount.value) {
            itemCount.value++
        }
    }
}

@Composable
private fun ClickableButton(
    text: String,
    itemCount: Int,
    onClick: (Int) -> Unit,
) {
    Box(modifier = Modifier
        .padding(16.dp)
        .clickable { onClick(itemCount) }
        .clip(RoundedCornerShape(8.dp))
        .background(PrimaryTextAndIconColor)
        .size(32.dp),
        contentAlignment = Alignment.Center) {
        Title(title = text, color = Color.White, style = Typography.titleLarge)
    }
}