package com.lms.worldoflol.ui.screens.main.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lms.worldoflol.common.shimmerEffect
import com.lms.worldoflol.ui.theme.skeleton_color_40
import com.lms.worldoflol.ui.theme.textStyle28
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun ProfileHeaderContent(
    modifier: Modifier = Modifier,
    profileIconId: String,
    profileName: String
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            SubcomposeAsyncImage(
                model = "http://ddragon.leagueoflegends.com/cdn/12.21.1/img/profileicon/${profileIconId}.png",
                loading = {
                    Spacer(modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(backgroundColor = skeleton_color_40)
                    )
                },
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(size = 120.dp)
                    .backgroundWithBorder(borderColor = 0xFFCA9D4B)
            )

            Text(
                text = profileName,
                style = textStyle28(color = 0xFFEEE2CC),
                modifier = Modifier.padding(bottom = 20.dp, start = 18.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProfileHeaderContentPreview() {
    ProfileHeaderContent(profileIconId = "0", profileName = "i am high")
}