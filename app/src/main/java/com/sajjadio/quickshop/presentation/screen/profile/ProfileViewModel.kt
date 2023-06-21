package com.sajjadio.quickshop.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.useCase.GetUserInformationUseCase
import com.sajjadio.quickshop.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        viewModelScope.launch {
            getUserInformationUseCase(USER_ID).collect { resource ->
                when (resource) {
                    Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                user = resource.data?.first(),
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = resource.errorMessage.toString()
                        )
                    }
                }
            }
        }

    }

    private companion object {
        const val USER_ID = 1
    }
}