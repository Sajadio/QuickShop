package com.sajjadio.quickshop.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sajjadio.quickshop.presentation.ui.theme.Poppins

@Composable
fun UserNameText(
    text: String,
    fontSize: Int = 14,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold
    )
}