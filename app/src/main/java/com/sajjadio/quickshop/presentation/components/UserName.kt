package com.sajjadio.quickshop.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sajjadio.quickshop.presentation.ui.theme.Poppins

@Composable
fun UserName(
    text: String,
    fontSize: Int = 14,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold
    )
}