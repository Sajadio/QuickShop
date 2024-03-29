package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryColor

@Composable
fun CircularProgressBarContainer(
    size: Int
) {
    if (size == 0) {
        CircularProgressBar()
    } else {
        CircularProgressBar(Modifier.size(size = size.dp))
    }
}

@Composable
private fun CircularProgressBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = SecondaryColor, modifier = modifier)
    }
}
