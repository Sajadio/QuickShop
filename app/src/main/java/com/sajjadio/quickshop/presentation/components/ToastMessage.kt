package com.sajjadio.quickshop.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
fun ToastMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    val toast = remember { Toast.makeText(context, message, duration) }
    DisposableEffect(Unit) {
        toast.show()
        onDispose {
            toast.cancel()
        }
    }
}
