package com.sajjadio.quickshop.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.R

@Composable
fun ImageProfile(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        painter = painter,
        contentDescription = stringResource(id = R.string.image_profile),
        contentScale = ContentScale.FillWidth
    )
}