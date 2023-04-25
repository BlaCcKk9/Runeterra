package com.lms.worldoflol.ui.screens.main.champions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lms.worldoflol.R
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.ui.theme.textStyle18
import com.lms.worldoflol.utils.backgroundWithBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.FilterQuality
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.util.CoilUtils
import com.lms.worldoflol.common.shimmerEffect
import com.lms.worldoflol.ui.theme.skeleton_color_40
import com.lms.worldoflol.ui.theme.skeleton_color_60

@Composable
fun ChampionItem(
    champion: Champion,
    onChampionClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(0.78f)
            .backgroundWithBorder(
                borderGradientColors = arrayListOf(0x4DCA9D4B, 0x4DEEE2CC)
            )
            .clickable { onChampionClick(champion.id) }
    ) {

        val painter = rememberAsyncImagePainter(
            model = champion.image,
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.High
        )

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_rectangle),
                contentScale = ContentScale.FillBounds,
                contentDescription = "background"
            )
            Text(text = champion.name, style = textStyle18(color = 0xCCEEE2CC))
        }
    }
}

@Preview
@Composable
fun ChampionItemPreview() {
    val champion = Champion(
        "Aatrox",
        "R.drawable.ic_aatrox",
        listOf(""),
        "",
        ""
    )
    ChampionItem(champion = champion) {}
}