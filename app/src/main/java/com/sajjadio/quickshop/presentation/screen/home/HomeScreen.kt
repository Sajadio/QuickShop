@file:OptIn(
    ExperimentalPagerApi::class, ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class
)

package com.sajjadio.quickshop.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.Dp
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
import com.sajjadio.quickshop.presentation.components.CategoryItem
import com.sajjadio.quickshop.presentation.components.ClickableIcon
import com.sajjadio.quickshop.presentation.components.ProductItem
import com.sajjadio.quickshop.presentation.components.ProfileImage
import com.sajjadio.quickshop.presentation.components.SearchBox
import com.sajjadio.quickshop.presentation.components.SpacerHorizontal
import com.sajjadio.quickshop.presentation.components.StaticIcon
import com.sajjadio.quickshop.presentation.components.UserName
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
    calculateBottomPadding: Dp,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val adsUIState = viewModel.adsUiState.value
    val categoryUiState = viewModel.categoryUiState.value
    val productUiState = viewModel.productUiState.value
    HomeContent(
        adsUIState,
        categoryUiState,
        productUiState,
        calculateBottomPadding
    )
}

@ExperimentalPagerApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    adsUIState: AdsUIState,
    categoryUiState: CategoryUiState,
    productUiState: ProductUiState,
    calculateBottomPadding: Dp
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    AppBar()
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = BaseColor,
                ),
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = calculateBottomPadding,
                top = it.calculateTopPadding(),
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { ContainerSearchBox() }
            item { SliderImage(adsUIState) }
            item { Categories(categoryUiState = categoryUiState) }
            item { Products(productUiState.products) }
        }
    }

}


@Composable
private fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileImage(painter = painterResource(id = R.drawable.details_image))
            SpacerHorizontal(width = 8)
            UserName(text = "Hi, John")
        }
        ClickableIcon(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "Notification",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { }
        )
    }
}

@Composable
private fun ContainerSearchBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalSlider(ads, state)
        IndicatorOfSliderImage(state = state)
    }
}

@ExperimentalPagerApi
@Composable
private fun HorizontalSlider(
    ads: List<Ads>,
    state: PagerState
) {
    HorizontalPager(
        count = ads.size,
        state = state,
        itemSpacing = 16.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize(),
        key = { ads[it].poster }
    ) { index ->
        SliderImage(ads, index)
    }
}

@Composable
private fun SliderImage(
    ads: List<Ads>,
    index: Int
) {
    Image(
        painter = rememberAsyncImagePainter(model = ads[index].poster),
        contentDescription = "",
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.FillWidth
    )
}

@ExperimentalPagerApi
@Composable
private fun IndicatorOfSliderImage(
    state: PagerState,
) {
    HorizontalPagerIndicator(
        pagerState = state,
        pageCount = state.pageCount,
        modifier = Modifier.padding(16.dp),
        activeColor = AccentColor,
        inactiveColor = SecondaryTextColor,
    )
}

@Composable
private fun CategoryHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Header(text = stringResource(id = R.string.shopping_by_category))
        ClickableHeader(text = stringResource(id = R.string.see_all)) {}
    }
}

@Composable
fun Categories(categoryUiState: CategoryUiState) {
    CategoryHeader()
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Header(text = stringResource(id = R.string.new_products))
        ClickableHeader(text = stringResource(id = R.string.see_all)) {}
    }
}

@Composable
fun Products(productUiState: List<Product>) {
    ProductHeader()
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(productUiState) { state ->
            ProductItem(
                state = state,
                onClick = {},
                onClickAddToCart = {}
            )
        }
    }
}

@Composable
private fun Header(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        color = PrimaryTextAndIconColor
    )
}

@Composable
private fun ClickableHeader(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        color = SecondaryColor,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    )
}

@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(0.dp)
}