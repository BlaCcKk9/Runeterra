package com.lms.worldoflol.ui.screens.main.champions.champion_detail.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lms.worldoflol.domain.model.remote.Spell
import com.lms.worldoflol.ui.theme.gradientText
import com.lms.worldoflol.utils.backgroundWithBorder

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AbilityItem(
    spell: Spell,
    selectedSpell: Spell,
    modifier: Modifier = Modifier,
    onClick: (Spell) -> Unit
) {
    val isSelected = selectedSpell == spell

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 16.dp, bottom = 50.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(spell.imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "ability_item",
            modifier = Modifier
                .size(50.dp)
                .drawColoredShadow(
                    alpha = if (isSelected) 1f else 0f,
                    color = Color(0xFFCA9D4B)
                )
                .backgroundWithBorder(
                    borderColor = if (isSelected) 0xFFCA9D4B else 0x80CA9D4B,
                    borderWidth = 0.5.dp
                )
                .clickable { onClick(spell) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = spell.name,
            style = gradientText(
                size = 14,
                color1 = if (isSelected) 0xFFF6C97F else 0x80F6C97F,
                color2 = if (isSelected) 0xFFCA9D4B else 0x80CA9D4B
            )
        )
    }
}