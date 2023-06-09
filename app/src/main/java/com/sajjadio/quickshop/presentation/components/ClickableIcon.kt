package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter


@Composable
fun ClickableIcon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .clickable { },
        painter = painter,
        contentDescription = contentDescription
    )
}