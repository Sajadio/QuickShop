package com.sajjadio.quickshop.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.ProfileImage
import com.sajjadio.quickshop.presentation.components.SpacerVertical
import com.sajjadio.quickshop.presentation.components.UserName
import com.sajjadio.quickshop.presentation.ui.theme.AccentColor
import com.sajjadio.quickshop.presentation.ui.theme.Tajawal
import com.sajjadio.quickshop.presentation.ui.theme.PrimaryTextAndIconColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    ProfileContent(
        state = viewModel.state.value
    )
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
        SpacerVertical(height = 32)
        ProfileImage(
            painter = rememberAsyncImagePainter(model = state.userImage),
            size = 100,
            borderColor = AccentColor
        )
        SpacerVertical(height = 8)
        UserName(text = state.userName, fontSize = 18)
        SpacerVertical(height = 32)
        Container(
            title = stringResource(id = R.string.order),
            description = "You have ${state.information.orderNumber} completed orders"
        )
        SpacerVertical(height = 8)
        Container(
            title = stringResource(id = R.string.shipping_address),
            description = "${state.information.shippingAddressNumber} addresses have been set"
        )
        SpacerVertical(height = 8)
        Container(
            title = stringResource(id = R.string.my_reviews),
            description = "Reviewed ${state.information.orderNumber} items"
        )
        SpacerVertical(height = 8)
    }
}

@Composable
fun Container(
    title: String,
    description: String
) {
    Box(
        modifier = Modifier
            .background(TextInputFiledColor)
            .fillMaxWidth()
            .height(75.dp)
            .padding(16.dp)
    ) {
        Column() {
            Title(
                title = title,
                fontSize = 14,
                fontWeight = FontWeight.SemiBold
            )
            SpacerVertical(height = 4)
            Title(
                title = description,
                fontSize = 12,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
private fun Title(
    title: String,
    fontSize: Int,
    fontWeight: FontWeight
) {
    Text(
        text = title,
        fontSize = fontSize.sp,
        color = PrimaryTextAndIconColor,
        fontWeight = fontWeight,
        fontFamily = Tajawal
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}