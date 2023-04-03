package com.lms.worldoflol.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.tierIcon(tier: String): Modifier {
    if (tier != "GOLD") {
        return padding(start = 16.dp, end = 16.dp).size(80.dp, 80.dp)
    }
    return padding(top = 5.dp)
}


