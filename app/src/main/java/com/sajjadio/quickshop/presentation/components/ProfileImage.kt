package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.R

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: Int = 45,
    borderColor: Color = Color.Transparent
) {
    Image(
        modifier = modifier
            .size(size.dp)
            .padding(4.dp)
            .border(1.dp, borderColor, CircleShape)
            .clip(CircleShape),
        painter = painter,
        contentDescription = stringResource(id = R.string.image_profile),
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.TopEnd
    )
}