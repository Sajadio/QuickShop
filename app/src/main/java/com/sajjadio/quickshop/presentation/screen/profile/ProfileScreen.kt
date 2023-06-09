package com.sajjadio.quickshop.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen() {
    ProfileContent()
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewProfileScreen(){
    ProfileScreen()
}