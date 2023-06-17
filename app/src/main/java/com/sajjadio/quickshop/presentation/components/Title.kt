package com.sajjadio.quickshop.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography

@Composable
fun Title(
    title: String,
    maxLine: Int = 1,
    style: TextStyle = AppTypography.titleMedium,
    color: Color = PrimaryTextAndIconColor,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = maxLine,
        color = color,
        overflow = TextOverflow.Ellipsis,
        style = style,
        textAlign = textAlign
    )
}