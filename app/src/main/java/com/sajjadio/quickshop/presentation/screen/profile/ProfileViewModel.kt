package com.sajjadio.quickshop.presentation.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(ProfileUiState())
    val state: State<ProfileUiState> = _state

    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        _state.value = ProfileUiState(
            userName = "John Doe",
            userImage = "https://th.bing.com/th/id/OIP.YRWxSR-98gcNp0gIu96JyAHaHa?w=205&h=205&c=7&r=0&o=5&dpr=1.3&pid=1.7"
        )
    }
}