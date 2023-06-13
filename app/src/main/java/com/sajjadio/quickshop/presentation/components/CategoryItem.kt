package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.presentation.screen.common.Category
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import com.sajjadio.quickshop.presentation.ui.theme.Typography

@Composable
fun CategoryItem(
    state: Category,
    modifier: Modifier = Modifier,
    onClickCategoryItem: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(130.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryCard(modifier, onClickCategoryItem, state)
        SpacerVertical(height = 8)
        Title(title = state.title, style = Typography.bodyLarge, textAlign = TextAlign.Center)
    }
}

@Composable
private fun CategoryCard(
    modifier: Modifier = Modifier,
    onClickCategoryItem: (String) -> Unit,
    state: Category
) {
    Card(
        modifier = modifier
            .size(100.dp)
            .clickable { onClickCategoryItem(state.title) },
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
