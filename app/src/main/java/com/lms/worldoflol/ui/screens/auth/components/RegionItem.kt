package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.common.Region
import com.lms.worldoflol.ui.theme.textStyle16

sealed class Regions(val name: String) {
    object Eune : Regions(Region.EUNE)
    object EUW : Regions(Region.EUW)
    object Korea : Regions(Region.KOREA)
    object Brazil : Regions(Region.BRAZIL)
    object LAN : Regions(Region.LAN)
    object LAS : Regions(Region.LAS)
    object NorthAmerica : Regions(Region.NORTH_AMERICA)
    object Oceania : Regions(Region.OCEANIA)
    object Russia : Regions(Region.RUSSIA)
    object Turkey : Regions(Region.TURKEY)
    object Japan : Regions(Region.JAPAN)

    companion object {
        var list = listOf(
            Eune, EUW, Korea, Brazil, LAN, LAS,
            NorthAmerica, Oceania, Russia, Turkey, Japan
        )
        var size = list.size
    }
}

@Composable
fun RegionItem(item: Regions, onRegionClicked: (region: Regions) -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = item.name,
        style = textStyle16(color = 0xFFEEE2CC, fontStyle = FontStyle.Italic),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRegionClicked.invoke(item) })

}