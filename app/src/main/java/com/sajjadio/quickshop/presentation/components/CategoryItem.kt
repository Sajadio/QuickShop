package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajjadio.quickshop.presentation.screen.home.Category
import com.sajjadio.quickshop.presentation.ui.theme.BaseColor
import com.sajjadio.quickshop.presentation.ui.theme.Poppins
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor

@Composable
fun CategoryItem(
    state: Category,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(130.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryCard(modifier, onClick, state)
        SpacerVertical(height = 8)
        CategoryTitle(state)
    }
}

@Composable
private fun CategoryCard(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    state: Category
) {
    Card(
        modifier = modifier
            .size(100.dp)
            .clickable { onClick(state.id) },
        colors = CardDefaults.cardColors(TextInputFiledColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CategoryImage(state)
        }
    }
}

@Composable
private fun CategoryImage(state: Category) {
    Image(
        painter = painterResource(id = state.poster),
        contentDescription = state.title,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(40.dp)
    )
}

@Composable
private fun CategoryTitle(state: Category) {
    Text(
        text = state.title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Poppins,
        color = PrimaryTextAndIconColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}