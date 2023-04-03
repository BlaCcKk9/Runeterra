package com.lms.worldoflol.ui.screens.main.champions.champion_detail.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lms.worldoflol.domain.model.remote.Skin
import com.lms.worldoflol.utils.backgroundWithBorder

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SkinItem(
    skin: Skin,
    selectedSkin: Skin,
    onClick: (Skin) -> Unit
) {
    val isSelected = selectedSkin == skin
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(skin.imageUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = "ability_item",
        modifier = Modifier
            .aspectRatio(1f)
            .drawColoredShadow(
                alpha = if (isSelected) 1f else 0f,
                color = Color(0xFFCA9D4B)
            )
            .backgroundWithBorder(
                borderColor = if (isSelected) 0xFFCA9D4B else 0x80CA9D4B,
                borderWidth = 0.5.dp
            )
            .clickable { onClick(skin) }
    )
}