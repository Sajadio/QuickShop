@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class
)

package com.sajjadio.quickshop.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.ClickableIcon
import com.sajjadio.quickshop.presentation.components.ImageProfile
import com.sajjadio.quickshop.presentation.components.SearchBox
import com.sajjadio.quickshop.presentation.components.SpacerHorizontal
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.components.StaticIcon
import com.sajjadio.quickshop.presentation.components.UserNameText
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.Poppins
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val adsUIState = viewModel.adsUiState.value
    val categoryUiState = viewModel.categoryUiState.value
    val productUiState = viewModel.productUiState.value
    HomeContent(adsUIState, categoryUiState, productUiState)
}

@ExperimentalPagerApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    adsUIState: AdsUIState,
    categoryUiState: CategoryUiState,
    productUiState: ProductUiState
) {
    Column() {
        AppBar()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        ) {
            item { ContainerSearchBox() }
            item { SliderImage(adsUIState) }
            item { CategoryHeader() }
            item { Categories(categoryUiState = categoryUiState) }
            item { ProductHeader() }
            item { Products(productUiState.products) }
        }
    }
}


@Composable
private fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageProfile(
            painter = painterResource(id = R.drawable.details_image)
        )
        SpacerHorizontal(width = 8)
        UserNameText(
            text = "Hi, John"
        )
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .weight(1f)
        ) {
            ClickableIcon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Notification"
            )
        }
    }
}

@Composable
private fun ContainerSearchBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchBox(modifier = Modifier.weight(0.8f))
        SpacerHorizontal(width = 16)
        FilterButton(modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun FilterButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(TextInputFiledColor)
            .height(56.dp)
            .fillMaxWidth()
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        StaticIcon(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = stringResource(id = R.string.filter),
        )
    }
}

@Composable
fun SliderImage(
    adsUIState: AdsUIState
) {
    SpacerVertical(height = 16)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState()
        AutoSliderImage(pagerState)
        SliderImageHorizontal(pagerState, adsUIState.ads)
    }
}

@Composable
fun AutoSliderImage(pagerState: PagerState) {
    LaunchedEffect(key1 = Unit) {
        while (pagerState.pageCount > pagerState.currentPage) {
            yield()
            delay(2000)
            tween<Float>(500)
            pagerState.animateScrollToPage(
                page = if (pagerState.pageCount != 0) {
                    (pagerState.currentPage + 1) % pagerState.pageCount
                } else pagerState.currentPage + 1
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun SliderImageHorizontal(
    state: PagerState,
    ads: List<Ads>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = ads.size,
            state = state,
            itemSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            key = { ads[it].poster }
        ) { index ->
            Image(
                painter = rememberAsyncImagePainter(model = ads[index].poster),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillWidth
            )
        }
        IndicatorOfSliderImage(state = state)
    }
}

@ExperimentalPagerApi
@Composable
private fun IndicatorOfSliderImage(
    state: PagerState,
) {
    HorizontalPagerIndicator(
        pagerState = state,
        pageCount = state.pageCount,
        modifier = Modifier
            .padding(16.dp),
        activeColor = AccentColor,
        inactiveColor = SecondaryTextColor,
    )
}

@Composable
private fun CategoryHeader() {
    SpacerVertical(height = 8)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TitleText(text = stringResource(id = R.string.shopping_by_category))
        ClickableText(text = stringResource(id = R.string.see_all)) {}
    }
}

@Composable
fun Categories(categoryUiState: CategoryUiState) {
    SpacerVertical(height = 8)
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            categoryUiState.categories
        ) {
            CategoryItem(it) {}
        }
    }
}

@Composable
fun ProductHeader() {
    SpacerVertical(height = 16)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TitleText(text = stringResource(id = R.string.new_products))
        ClickableText(text = stringResource(id = R.string.see_all)) {}
    }
}

@Composable
fun Products(productUiState: List<Product>) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(productUiState) {
            ProductItem(state = it, onClick = {}, onClickAddToCart = {})
        }
    }
}

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
            .height(500.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(BaseColor)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = state.poster),
            contentDescription = state.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .wrapContentHeight()
                .clickable { onClick(state.id) },
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.Start
        ) {
            SpacerVertical(height = 8)
            Text(
                text = state.title,
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                color = PrimaryTextAndIconColor
            )
            SpacerVertical(height = 4)
            Text(
                text = state.category,
                fontSize = 14.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                color = PrimaryTextAndIconColor
            )
            SpacerVertical(height = 4)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(state.rate)
                Text(
                    text = state.rate.toString(),
                    fontSize = 12.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    color = SecondaryTextColor
                )
            }
            SpacerVertical(height = 8)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${state.price}",
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    color = PrimaryTextAndIconColor
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onClickAddToCart(state.id) }
                        .size(48.dp)
                        .background(AccentColor)
                        .padding(12.dp)
                ) {
                    StaticIcon(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = "Add item to cart",
                        tint = BaseColor
                    )
                }
            }
        }
    }
}


@Composable
private fun TitleText(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        color = PrimaryTextAndIconColor
    )
}

@Composable
private fun ClickableText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        color = SecondaryColor,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    )
}

@Composable
fun CategoryItem(
    state: Category,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.background(BaseColor),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(TextInputFiledColor)
                .height(100.dp)
                .width(100.dp)
                .clickable {
                    onClick(state.id)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = state.poster),
                contentDescription = state.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
        }
        SpacerVertical(height = 8)
        Text(
            text = state.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = PrimaryTextAndIconColor
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun RatingBar(rating: Double) {
    val maxRating = 5
    Row(modifier = Modifier.padding(end = 8.dp)) {
        val outlineRating = maxRating - rating.toInt()
        repeat(rating.toInt()) {
            StaticIcon(
                painter = painterResource(id = R.drawable.ic_fill_star),
                contentDescription = "Filled Star",
                tint = SecondaryColor
            )
        }
        repeat(outlineRating) {
            StaticIcon(
                painter = painterResource(id = R.drawable.ic_outline_star),
                contentDescription = "Outlined Star",
                tint = SecondaryTextColor
            )
        }
    }
}

@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}