package com.sajjadio.quickshop.presentation.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpacerVertical(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}