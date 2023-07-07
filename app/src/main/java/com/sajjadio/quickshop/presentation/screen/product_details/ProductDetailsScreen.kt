package com.sajjadio.quickshop.presentation.screen.product_details

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import coil.compose.rememberAsyncImagePainter
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.CheckUiState
import com.sajjadio.quickshop.presentation.components.ContainerRating
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.components.Title
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography
import com.sajjadio.quickshop.presentation.ui.theme.LightAccentColor

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.uiState.collectAsState()

    ProductDetailsContent(
        itemCount = viewModel.itemCount,
        onClickBack = navController::popBackStack,
        state = state
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ProductDetailsContent(
    itemCount: MutableState<Int>,
    onClickBack: () -> Unit,
    state: ProductDetailsUiState
) {
    CheckUiState(
        isLoading = state.isLoading,
        error = state.error,
        data = state.product
    ) { product ->
        ProductContainer(
            onClickBack,
            itemCount,
            product
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductContainer(
    onClickBack: () -> Unit,
    itemCount: MutableState<Int>,
    product: Product
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            stickyHeader {
                BackButton(onClickBack)
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    ProductImage(product.image)
                }
            }

            item {
                ProductDetails(product)
            }
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
private fun ProductImage(image: String) {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = rememberAsyncImagePainter(model = image),
        contentDescription = "product image",
        alignment = Alignment.TopCenter,
    )
}

@Composable
private fun BackButton(onClickBack: () -> Unit) {
    Box(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(LightAccentColor)
                .clickable { onClickBack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_left_back),
                contentDescription = "Previous screen"
            )
        }
    }
}

@Composable
private fun ProductDetails(product: Product) {
    Column(modifier = Modifier.padding(16.dp)) {
        Title(
            title = product.title,
            style = AppTypography.bodyLarge,
            maxLine = 2
        )
        Body(
            title = product.category,
            maxLine = 2
        )
        ContainerRating(product.rating.rate)
        Body(
            title = product.description,
            maxLine = 20,
            style = AppTypography.labelSmall,
        )
        SpacerVertical(height = 16)
        Title(title = "$${product.price}", style = AppTypography.bodyLarge)
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
                style = AppTypography.bodyLarge
            )
        }
    }
}

@Composable
private fun ContainerClickableButtons(itemCount: MutableState<Int>) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .width(164.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ClickableButton("-", itemCount = itemCount.value) {
            if (it > 0)
                itemCount.value--
        }
        Title(title = itemCount.value.toString(), style = AppTypography.titleLarge)
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
        Title(title = text, color = Color.White, style = AppTypography.titleLarge)
    }
}