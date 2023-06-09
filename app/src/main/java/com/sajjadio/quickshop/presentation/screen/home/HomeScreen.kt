package com.sajjadio.quickshop.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.sajjadio.quickshop.presentation.composable.ClickableIcon
import com.sajjadio.quickshop.presentation.composable.ImageProfile
import com.sajjadio.quickshop.presentation.composable.SpacerHorizontal
import com.sajjadio.quickshop.presentation.composable.SpacerVertical
import com.sajjadio.quickshop.presentation.composable.StaticIcon
import com.sajjadio.quickshop.presentation.composable.UserNameText
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
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
    HomeContent(adsUIState)
}

@ExperimentalPagerApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(adsUIState: AdsUIState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
        SpacerVertical(height = 16)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(TextInputFiledColor)
                    .height(56.dp)
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .fillMaxWidth()
                    .weight(0.8f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StaticIcon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.search),
                    tint = SecondaryTextColor
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.search),
                    fontSize = 12.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    color = SecondaryTextColor,
                    textAlign = TextAlign.Center
                )
            }
            SpacerHorizontal(width = 16)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(TextInputFiledColor)
                    .height(56.dp)
                    .fillMaxWidth()
                    .weight(0.2f)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                StaticIcon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.filter),
                )
            }
        }
        SpacerVertical(height = 16)

        val pagerState = rememberPagerState()
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SliderImageHorizontal(pagerState, adsUIState.ads)
            IndicatorOfSliderImage(pagerState)
        }

        SpacerVertical(height = 16)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleText(text = stringResource(id = R.string.new_products))
            ClickableText(text = stringResource(id = R.string.see_all)) {}
        }
        SpacerVertical(height = 8)
        LazyRow {

        }
    }
}

@ExperimentalPagerApi
@Composable
private fun SliderImageHorizontal(
    state: PagerState,
    ads: List<Ads>
) {
    HorizontalPager(
        count = ads.size,
        state = state,
        itemSpacing = 8.dp,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        key = { ads[it].poster }
    ) { index ->
        Image(
            painter = rememberAsyncImagePainter(model = ads[index].poster),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
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

@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}