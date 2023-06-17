package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryColor
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography
import com.sajjadio.quickshop.presentation.ui.theme.Tajawal

@Composable
fun ContainerRating(
    rate: Double
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingBar(rate.toInt())
        SpacerHorizontal(width = 4)
        Text(
            text = rate.toString(),
            color = SecondaryTextColor,
            textAlign = TextAlign.Center,
            fontFamily = Tajawal,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RatingBar(
    rating: Int,
    maxRating: Int = 5
) {
    val outlineRating = maxRating - rating
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AddRatingBar(
            rating = rating,
            painter = painterResource(id = R.drawable.ic_fill_star),
            description = "Filled star",
            tint = SecondaryColor
        )
        AddRatingBar(
            rating = outlineRating,
            painter = painterResource(id = R.drawable.ic_outline_star),
            description = "Outlined star ",
            tint = SecondaryTextColor
        )
    }
}

@Composable
private fun AddRatingBar(
    rating: Int,
    painter: Painter,
    description: String,
    tint: Color
) {
    repeat(rating) {
        StaticIcon(
            painter = painter,
            contentDescription = description,
            tint = tint
        )
    }
}