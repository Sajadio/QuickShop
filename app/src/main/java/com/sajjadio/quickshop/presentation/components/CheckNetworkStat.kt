package com.sajjadio.quickshop.presentation.components

import androidx.compose.runtime.Composable
import kotlin.properties.Delegates


private var isSuccess by Delegates.notNull<Boolean>()

@Composable
fun CheckUiState(
    isLoading: Boolean,
    error: String,
    onSuccess: @Composable (Boolean) -> Unit
) {

    isSuccess = if (error.isNotEmpty()) {
        ErrorBox(text = error)
        false
    } else {
        true
    }
    isSuccess = if (isLoading) {
        CircularProgressBar()
        false
    } else {
        true
    }
    onSuccess(isSuccess)
}