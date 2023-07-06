package com.sajjadio.quickshop.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor():ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val iaLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000L)
            _isLoading.value = false
        }
    }
}