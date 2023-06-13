package com.sajjadio.quickshop.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sajjadio.quickshop.presentation.ui.theme.Typography

@Composable
fun Body(
    title: String,
    maxLine: Int = 1,
    style: TextStyle = Typography.bodySmall,
    color: Color = PrimaryTextAndIconColor,
    textAlign: TextAlign = TextAlign.Start
) {
    Title(title = title, maxLine = maxLine, style = style, color = color, textAlign = textAlign)
}