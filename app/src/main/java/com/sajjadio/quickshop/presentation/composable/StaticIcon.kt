package com.sajjadio.quickshop.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter


@Composable
fun StaticIcon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription
    )
}