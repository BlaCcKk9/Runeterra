package com.lms.worldoflol.ui.screens.main.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.ui.theme.textStyle12

@Composable
fun EmptyRegionWarning(
    visible: Boolean,
    warningText: String,
    warningIcon: Int,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(visible = visible) {
        Row(modifier = modifier) {
            Icon(
                painter = painterResource(id = warningIcon),
                tint = Color(0xFFCA4B4B),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = warningText,
                style = textStyle12(0xFFCA4B4B)
            )
        }
    }
}