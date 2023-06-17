package com.sajjadio.quickshop.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.components.Body
import com.sajjadio.quickshop.presentation.components.SpacerHorizontal
import com.sajjadio.quickshop.presentation.components.StaticIcon
import com.sajjadio.quickshop.presentation.ui.theme.Tajawal
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.AppTypography
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    onClickSearchBox: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(TextInputFiledColor)
            .height(56.dp)
            .fillMaxWidth()
            .clickable { onClickSearchBox() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SpacerHorizontal(width = 16)
        StaticIcon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(id = R.string.search),
            tint = SecondaryTextColor
        )
        SpacerHorizontal(width = 8)
        Body(
            title = stringResource(id = R.string.search),
            style = AppTypography.bodySmall,
            color = SecondaryTextColor,
            textAlign = TextAlign.Center
        )
    }
}