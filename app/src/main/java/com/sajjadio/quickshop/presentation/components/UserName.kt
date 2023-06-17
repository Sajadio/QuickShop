package com.sajjadio.quickshop.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

@Composable
fun UserName(
    text: String,
    style: TextStyle
) {
    Text(
        text = text,
        style = style,
    )
}