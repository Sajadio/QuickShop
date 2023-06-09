package com.sajjadio.quickshop.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor


@Composable
fun StaticIcon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = PrimaryTextAndIconColor
) {
    Icon(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        tint = tint
    )
}