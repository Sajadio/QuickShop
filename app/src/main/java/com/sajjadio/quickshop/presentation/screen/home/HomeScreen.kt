@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)

package com.sajjadio.quickshop.presentation.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

@Composable
fun HomeScreen() {
    HomeContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent() {

    val images = listOf(
        "https://mir-s3-cdn-cf.behance.net/project_modules/disp/3c6e6716432985.562abb413f08e.jpg",
        "https://i2-prod.mirror.co.uk/incoming/article8687186.ece/ALTERNATES/s615/Main-VIP-air-stewardess-reveals-exactly-what-goes-on-aboard-private-jets.jpg",
        "https://th.bing.com/th/id/OIP.N1m4xKxK4RnwQbtKSm5TUAHaE8?pid=ImgDet&w=600&h=400&rs=1",
        "https://th.bing.com/th/id/OIP.uAoQWoZVeYBiU0Tjj4BhkAHaE8?pid=ImgDet&w=500&h=334&rs=1"
    )

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
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(TextInputFiledColor)
                    .weight(0.8f)
                    .height(56.dp)
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
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
                    .weight(0.2f)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                StaticIcon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.filter)
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
            HorizontalPager(
                count = images.size,
                state = pagerState,
                itemSpacing = 8.dp,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                key = { images[it] }
            ) { index ->
                Image(
                    painter = rememberAsyncImagePainter(model = images[index]),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = pagerState.pageCount,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = AccentColor,
                inactiveColor = SecondaryTextColor,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}