package com.sajjadio.quickshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.screen.home.utils.SortOption
import com.sajjadio.quickshop.presentation.ui.theme.TextInputFiledColor

@Composable
fun SortButton(
    modifier: Modifier = Modifier,
    onMenuItemClick: (SortOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(TextInputFiledColor)
            .height(56.dp)
            .fillMaxWidth()
            .clickable { expanded = true },
        contentAlignment = Alignment.Center
    ) {
        StaticIcon(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = stringResource(id = R.string.filter),
        )
    }

    DropdownMenu(
        expanded = expanded,
        offset = DpOffset(x = (-24).dp, y = 0.dp),
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                onMenuItemClick(SortOption.All)
                expanded = false
            }
        ) {
            Text(SortOption.All.name)
        }
        DropdownMenuItem(
            onClick = {
                onMenuItemClick(SortOption.Asc)
                expanded = false
            }
        ) {
            Text(SortOption.Asc.name)
        }
        DropdownMenuItem(
            onClick = {
                onMenuItemClick(SortOption.Desc)
                expanded = false
            }
        ) {
            Text(SortOption.Desc.name)
        }
    }
}
