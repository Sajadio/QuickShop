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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.CategoryItem
import com.sajjadio.quickshop.presentation.components.CheckUiState
import com.sajjadio.quickshop.presentation.components.ClickableIcon
import com.sajjadio.quickshop.presentation.components.ProductItem
import com.sajjadio.quickshop.presentation.components.ProfileImage
import com.sajjadio.quickshop.presentation.screen.home.components.SearchBox
import com.sajjadio.quickshop.presentation.components.SpacerHorizontal
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.components.StaticIcon
import com.sajjadio.quickshop.presentation.components.Title
import com.sajjadio.quickshop.presentation.components.UserName
import com.sajjadio.quickshop.presentation.screen.categories.navigateToCategories
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import com.sajjadio.quickshop.presentation.screen.product_details.navigateToProductDetails
import com.sajjadio.quickshop.presentation.screen.products.navigateToProducts
import com.sajjadio.quickshop.presentation.screen.productsByCategory.navigateToProductsByCategory
import com.sajjadio.quickshop.presentation.screen.search.navigateToSearchScreen
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    calculateBottomPadding: Dp,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val adsUiState by viewModel.adUiState.collectAsState()
    val categoriesUiState by viewModel.categoryUiState.collectAsState()
    val productsUiState by viewModel.productUiState.collectAsState()
    HomeContent(
        adsUiState,
        categoriesUiState,
        productsUiState,
        calculateBottomPadding,
        onClickProducts = navController::navigateToProducts,
        onClickProductItem = navController::navigateToProductDetails,
        onClickCategories = navController::navigateToCategories,
        onClickCategoryItem = navController::navigateToProductsByCategory,
        onClickAddToCart = {},
        onClickSearchBox = navController::navigateToSearchScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalPagerApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    adsUiState: AdUIState,
    categoriesUiState: CategoryUiState,
    productsUiState: ProductUiState,
    calculateBottomPadding: Dp,
    onClickProducts: () -> Unit,
    onClickProductItem: (Int) -> Unit,
    onClickAddToCart: (Int) -> Unit,
    onClickCategories: () -> Unit,
    onClickCategoryItem: (String) -> Unit,
    onClickSearchBox: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = { AppBar() },
                scrollBehavior = scrollBehavior,
            )
        },
        containerColor = BaseColor
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = PaddingValues(
                bottom = calculateBottomPadding,
                top = paddingValues.calculateTopPadding(),
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { ContainerSearchBox(onClickSearchBox = onClickSearchBox) }
            item {
                CheckUiState(
                    isLoading = adsUiState.isLoading,
                    error = adsUiState.error,
                    data = adsUiState.ads,
                    sizeOfProgress = 30
                ) { ads ->
                    SliderImage(ads)
                }
            }
            item {
                CategoryHeader(onClickCategories = onClickCategories)
                SpacerVertical(height = 8)
                CheckUiState(
                    isLoading = categoriesUiState.isLoading,
                    error = categoriesUiState.error,
                    data = categoriesUiState.categories,
                    sizeOfProgress = 30
                ) { categories ->
                    Categories(
                        categoryUiState = categories,
                        onClickCategoryItem = onClickCategoryItem
                    )
                }
            }
            item {
                SpacerVertical(height = 16)
                ProductHeader {
                    onClickProducts()
                }
                SpacerVertical(height = 8)
                CheckUiState(
                    isLoading = productsUiState.isLoading,
                    error = productsUiState.error,
                    productsUiState.products,
                    sizeOfProgress = 30
                ) { products ->
                    Products(
                        products,
                        onClickItem = onClickProductItem,
                        onClickAddToCart = onClickAddToCart
                    )
                }
            }
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
            UserName(text = "Hi, John", style = AppTypography.titleLarge)
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
private fun ContainerSearchBox(onClickSearchBox: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchBox(
            modifier = Modifier.weight(0.8f),
            onClickSearchBox = onClickSearchBox
        )
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
    ads: List<Ad>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState()
        AutoSliderImage(pagerState)
        SliderImageHorizontal(pagerState, ads)
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
    ads: List<Ad>
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
    ads: List<Ad>,
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
        key = { ads[it].image }
    ) { index ->
        SliderImage(ads, index)
    }
}

@Composable
private fun SliderImage(
    ads: List<Ad>,
    index: Int
) {
    Image(
        painter = rememberAsyncImagePainter(model = ads[index].image),
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
fun Categories(
    categoryUiState: List<String>?,
    onClickCategoryItem: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categoryUiState?.let { categories ->
            items(categories) { category ->
                CategoryItem(category) { onClickCategoryItem(it) }
            }
        }
    }
}

@Composable
private fun CategoryHeader(
    onClickCategories: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Header(text = stringResource(id = R.string.shopping_by_category))
        ClickableHeader(text = stringResource(id = R.string.see_all)) { onClickCategories() }
    }
}

@Composable
fun ProductHeader(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Header(text = stringResource(id = R.string.new_products))
        ClickableHeader(text = stringResource(id = R.string.see_all)) { onClick() }
    }
}

@Composable
fun Products(
    productUiState: List<Product>?,
    onClickItem: (Int) -> Unit,
    onClickAddToCart: (Int) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        productUiState?.let {
            items(productUiState) { state ->
                ProductItem(
                    state = state,
                    onClickItem = onClickItem,
                    onClickAddToCart = onClickAddToCart
                )
            }
        }
    }
}

@Composable
private fun Header(text: String) {
    Title(title = text, style = AppTypography.titleLarge)
}

@Composable
private fun ClickableHeader(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Body(
        title = text,
        style = AppTypography.bodyLarge,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        color = SecondaryColor
    )
}
