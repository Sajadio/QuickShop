package com.sajjadio.quickshop.presentation.components

import androidx.compose.runtime.Composable
import kotlin.properties.Delegates


private var isSuccess by Delegates.notNull<Boolean>()

@Composable
fun <T> CheckUiState(
    isLoading: Boolean,
    error: String,
    data: T?,
    sizeOfProgress: Int = 0,
    onSuccess: @Composable (T) -> Unit
) {

    isSuccess = if (error.isNotEmpty()) {
        ErrorBox(text = error)
        false
    } else {
        true
    }
    isSuccess = if (isLoading) {
        CircularProgressBarContainer(sizeOfProgress)
        false
    } else {
        true
    }
    if (isSuccess) {
        data?.let {
            onSuccess(data)
        }
    }
}