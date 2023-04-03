package com.lms.worldoflol.ui.screens.main.champions.champion_detail.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.domain.model.remote.ChampionDetail
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.components.AbilityItem
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.components.championDetailsHeader
import com.lms.worldoflol.ui.theme.gradientText
import com.lms.worldoflol.ui.theme.textStyle14

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AbilitiesPage(championDetail: ChampionDetail) {

    var selectedSpellTitle by remember { mutableStateOf(championDetail.spells[0].title) }
    var selectedSpellDescription by remember { mutableStateOf(championDetail.spells[0].description) }
    var selectedSpell by remember { mutableStateOf(championDetail.spells[0]) }

    Column(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyRow(
            modifier = Modifier.championDetailsHeader(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(championDetail.spells) {
                AbilityItem(
                    spell = it,
                    selectedSpell = selectedSpell,
                ) { spell ->
                    selectedSpellTitle = spell.title
                    selectedSpellDescription = spell.description
                    selectedSpell = spell
                }
            }
        }

        AbilitiesBodyContent(
            spellTitle = selectedSpellTitle,
            spellDescription = selectedSpellDescription
        )
    }
}

@Composable
fun AbilitiesBodyContent(
    spellTitle: String,
    spellDescription: String
) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        modifier = Modifier.padding(start = 32.dp),
        text = spellTitle,
        style = gradientText(
            size = 16,
            color1 = 0xFFF6C97F,
            color2 = 0xFFCA9D4B
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
        text = spellDescription,
        style = textStyle14(
            color = 0xFFEEE2CC,
            textAlign = TextAlign.Start
        )
    )
}
