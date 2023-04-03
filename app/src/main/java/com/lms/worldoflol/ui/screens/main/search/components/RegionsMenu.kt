package com.lms.worldoflol.ui.screens.main.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.common.DropDown
import com.lms.worldoflol.common.MenuLazyColumn
import com.lms.worldoflol.ui.screens.auth.components.Regions
import com.lms.worldoflol.ui.theme.textStyle16

@Composable
fun RegionsMenu(
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    onRegionClick: (region: String) -> Unit
) {
    DropDown(
        initiallyOpened = visible,
        modifier = modifier
    ) {
        MenuLazyColumn{
            items(Regions.size, key = { it }) { index ->
                RegionItem(region = Regions.list[index]) {
                    onRegionClick(it)
                }
            }
        }
    }
}

@Composable
private fun RegionItem(
    region: Regions,
    onItemClick: (region: String) -> Unit
) {
    Text(
        text = region.name,
        style = textStyle16(color = 0xFFEEE2CC, fontStyle = FontStyle.Italic),
        modifier = Modifier
            .padding(bottom = 20.dp)
            .clickable { onItemClick(region.name) }
    )
}
