package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    painter: Painter,
    onClickBack: () -> Unit
) {

    TopAppBar(
        modifier = Modifier.background(Color.Blue),
        title = {
            Text(text = title, style = AppTypography.titleLarge)
        },
        navigationIcon = {
            IconButton(
                onClick = { onClickBack() }
            ) {
                Icon(
                    painter = painter,
                    contentDescription = "Previous screen",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    )
}