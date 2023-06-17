package com.sajjadio.quickshop.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography

@Composable
fun Body(
    title: String,
    maxLine: Int = 1,
    style: TextStyle = AppTypography.bodySmall,
    color: Color = PrimaryTextAndIconColor,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Title(
        modifier = modifier,
        title = title,
        maxLine = maxLine,
        style = style,
        color = color,
        textAlign = textAlign
    )
}