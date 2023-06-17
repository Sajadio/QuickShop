package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor
import java.util.Locale

@Composable
fun CategoryItem(
    title: String = "",
    onClickCategoryItem: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(TextInputFiledColor)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickCategoryItem(title) }
            .padding(8.dp)
    ) {
        Title(
            title = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = AppTypography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
