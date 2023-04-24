package com.lms.worldoflol.ui.screens.main.search.profile_detail.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.common.shimmerEffect
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.ui.screens.main.profile.components.EntryItem
import com.lms.worldoflol.ui.theme.skeleton_color_40
import com.lms.worldoflol.ui.theme.skeleton_color_60

@Composable
fun RanksPage(entries: List<Entry?>) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 25.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = false
    ) {
        items(entries.size, key = { it }) {
            EntryItem(entry = entries[it], position = it)
        }
    }
}

@Composable
fun RanksPageSkeleton() {
    Column(
        modifier = Modifier
            .padding(top = 25.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(2) {
            Spacer(
                modifier = Modifier
                    .width(124.dp)
                    .height(15.dp)
                    .shimmerEffect(
                        backgroundColor = skeleton_color_40,
                        shape = RoundedCornerShape(3.dp)
                    )
                    .align(Alignment.Start)
            )

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(120.dp)
                    .shimmerEffect(backgroundColor = skeleton_color_40),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(end = 16.dp, start = 16.dp)
                        .size(88.dp)
                        .shimmerEffect(backgroundColor = skeleton_color_60)
                )
                Column(Modifier.wrapContentHeight()) {
                    Spacer(
                        modifier = Modifier
                            .size(78.dp, 26.dp)
                            .shimmerEffect(
                                backgroundColor = skeleton_color_60,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                    Spacer(
                        modifier = Modifier
                            .size(48.dp, 16.dp)
                            .shimmerEffect(
                                backgroundColor = skeleton_color_60,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                    Spacer(
                        modifier = Modifier
                            .size(124.dp, 16.dp)
                            .shimmerEffect(
                                backgroundColor = skeleton_color_60,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
            }
        }
    }
}