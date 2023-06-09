package com.sajjadio.quickshop.presentation.screen.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CartScreen() {
    CartContent()
}

@Composable
fun CartContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cart Screen")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCartScreen(){
    CartScreen()
}