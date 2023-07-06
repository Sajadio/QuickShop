package com.sajjadio.quickshop.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.CheckUiState
import com.sajjadio.quickshop.presentation.components.ProfileImage
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.components.Title
import com.sajjadio.quickshop.presentation.components.UserName
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography
import com.sajjadio.quickshop.presentation.ui.theme.CardBackgroundColor

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    ProfileContent(state)
}

@Composable
fun ProfileContent(
    state: ProfileUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CheckUiState(
            isLoading = state.isLoading,
            error = state.error,
            data = state.user
        ) { user ->
            SpacerVertical(height = 32)
            ProfileImage(
                painter = painterResource(id = R.drawable.details_image),
                size = 100,
                borderColor = AccentColor
            )
            SpacerVertical(height = 8)
            UserName(text = user.username, style = AppTypography.titleLarge)
            SpacerVertical(height = 32)
            Container(
                title = stringResource(id = R.string.order),
                description = "You have ${3} completed orders"
            )
            SpacerVertical(height = 8)
            Container(
                title = stringResource(id = R.string.shipping_address),
                description = "${1} addresses have been set"
            )
            SpacerVertical(height = 8)
            Container(
                title = stringResource(id = R.string.my_reviews),
                description = "Reviewed ${4} items"
            )
            SpacerVertical(height = 8)
        }
    }
}

@Composable
fun Container(
    title: String,
    description: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CardBackgroundColor)
            .fillMaxWidth()
            .height(75.dp)
            .padding(16.dp)
    ) {
        Column() {
            Title(title = title, style = AppTypography.bodyLarge)
            SpacerVertical(height = 4)
            Body(title = description, style = AppTypography.labelSmall)
        }
    }
}
