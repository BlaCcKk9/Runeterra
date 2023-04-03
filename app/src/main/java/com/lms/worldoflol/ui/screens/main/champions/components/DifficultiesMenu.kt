package com.lms.worldoflol.ui.screens.main.champions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.common.DropDown
import com.lms.worldoflol.common.MenuLazyColumn

sealed class Difficulty(val icon: Int, val title: String){
    object Easy : Difficulty(R.drawable.ic_easy, "Low")
    object Medium : Difficulty(R.drawable.ic_medium, "Moderate")
    object Hard : Difficulty(R.drawable.ic_hard, "Hard")

    companion object {
        val difficulties = listOf(
            Easy, Medium, Hard
        )
    }
}

@Composable
fun BoxScope.DifficultiesMenu(
    visible: Boolean = false,
    width: Dp,
    onRoleClick: (Difficulty) -> Unit
) {
    val difficulties = Difficulty.difficulties
    DropDown(
        initiallyOpened = visible,
        alignment = Alignment.TopEnd,
        modifier = Modifier.padding(top = 120.dp, end = 16.dp)
    ) {
        MenuLazyColumn(
            width = width,
            shouldContentCenter = true
        ) {
            items(
                count = difficulties.size,
                key = { key -> key }
            ) { index -> DifficultysItem(difficulty = difficulties[index]) { onRoleClick(it) } }
        }
    }
}

@Composable
private fun DifficultysItem(
    difficulty: Difficulty,
    onItemClick: (difficulty: Difficulty) -> Unit
) {
    val difficultyIcon = difficulty.icon

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = difficultyIcon),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .height(8.dp)
                .width(100.dp)
                .clickable { onItemClick(difficulty) },
            contentScale = ContentScale.FillBounds,
            contentDescription = "difficulty_icon"
        )
    }
}
