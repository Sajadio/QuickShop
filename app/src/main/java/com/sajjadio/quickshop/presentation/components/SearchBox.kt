package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.background
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
import com.sajjadio.quickshop.presentation.ui.theme.Poppins
import com.sajjadio.quickshop.presentation.ui.theme.SecondaryTextColor
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor

@Composable
fun SearchBox(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(TextInputFiledColor)
            .height(56.dp)
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StaticIcon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(id = R.string.search),
            tint = SecondaryTextColor
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.search),
            fontSize = 12.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            color = SecondaryTextColor,
            textAlign = TextAlign.Center
        )
    }
}