package com.lms.worldoflol.ui.screens.main.profile.match_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.common.shimmerEffect
import com.lms.worldoflol.ui.theme.skeleton_color_40
import com.lms.worldoflol.ui.theme.skeleton_color_60

@Composable
fun MatchDetailSkeleton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF242731))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(86.dp)
                .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                .shimmerEffect(
                    backgroundColor = skeleton_color_40,
                    shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(106.dp)
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .background(Color(0x59242731))
            )
            Column(
                modifier = Modifier
                    .padding(start = 82.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterStart),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(
                    modifier = Modifier
                        .width(99.dp)
                        .height(26.dp)
                        .shimmerEffect(
                            backgroundColor = skeleton_color_60,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Spacer(
                    modifier = Modifier
                        .width(54.dp)
                        .height(15.dp)
                        .shimmerEffect(
                            backgroundColor = skeleton_color_60,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }

            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterEnd),
                horizontalAlignment = Alignment.End
            ) {
                Spacer(
                    modifier = Modifier
                        .width(116.dp)
                        .height(13.dp)
                        .shimmerEffect(
                            backgroundColor = skeleton_color_60,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Spacer(
                    modifier = Modifier
                        .width(125.dp)
                        .height(13.dp)
                        .shimmerEffect(
                            backgroundColor = skeleton_color_60,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier
                    .width(162.dp)
                    .height(15.dp)
                    .shimmerEffect(
                        backgroundColor = skeleton_color_40,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .align(Alignment.CenterStart)
            )
            Spacer(
                modifier = Modifier
                    .width(61.dp)
                    .height(13.dp)
                    .shimmerEffect(
                        backgroundColor = skeleton_color_40,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        repeat(7) {
            Box(
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .fillMaxWidth()
                    .height(108.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .shimmerEffect(backgroundColor = skeleton_color_40),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .wrapContentSize()
                        .align(Alignment.CenterStart)
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(131.dp)
                            .height(18.dp)
                            .shimmerEffect(
                                backgroundColor = skeleton_color_60,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Spacer(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .shimmerEffect(backgroundColor = skeleton_color_60)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(2) {
                                Spacer(
                                    modifier = Modifier
                                        .width(22.dp)
                                        .height(22.dp)
                                        .shimmerEffect(
                                            backgroundColor = skeleton_color_60,
                                            RoundedCornerShape(8.dp)
                                        )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(2) {
                                Spacer(
                                    modifier = Modifier
                                        .width(22.dp)
                                        .height(22.dp)
                                        .shimmerEffect(
                                            backgroundColor = skeleton_color_60,
                                            RoundedCornerShape(8.dp)
                                        )
                                )
                            }
                        }

                    }
                }


                Column(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .fillMaxHeight()
                        .wrapContentHeight()
                        .align(Alignment.CenterEnd),
                    horizontalAlignment = Alignment.End
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(52.dp)
                            .height(18.dp)
                            .shimmerEffect(
                                backgroundColor = skeleton_color_60,
                                RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        repeat(6) {
                            Spacer(
                                modifier = Modifier
                                    .width(22.dp)
                                    .height(22.dp)
                                    .shimmerEffect(
                                        backgroundColor = skeleton_color_60,
                                        RoundedCornerShape(8.dp)
                                    )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Spacer(
                        modifier = Modifier
                            .width(43.dp)
                            .height(13.dp)
                            .shimmerEffect(
                                backgroundColor = skeleton_color_60,
                                RoundedCornerShape(4.dp)
                            )
                    )
                }
            }
        }
    }
}