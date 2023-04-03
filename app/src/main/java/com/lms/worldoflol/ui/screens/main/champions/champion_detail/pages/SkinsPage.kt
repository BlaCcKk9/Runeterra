package com.lms.worldoflol.ui.screens.main.champions.champion_detail.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.domain.model.remote.Skin
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.components.SkinItem
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.components.championDetailsHeader

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SkinsPage(
    skins: List<Skin>,
    onSkinSelect: (Skin) -> Unit
) {
    val lazyListState = rememberLazyGridState()
    var selectedSkin by remember { mutableStateOf(skins[0]) }

    Column(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyVerticalGrid(
            state = lazyListState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 40.dp, start = 13.dp, end = 13.dp),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(skins.size) {
                SkinItem(
                    skin = skins[it],
                    selectedSkin = selectedSkin
                ) { skin ->
                    selectedSkin = skin
                    onSkinSelect(skin)
                }
            }
        }
    }
}
