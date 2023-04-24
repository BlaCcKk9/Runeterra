package com.lms.worldoflol.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lms.worldoflol.R
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun BoxScope.BackButton(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    onBackPressed: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp)
            .size(50.dp)
            .backgroundWithBorder(
                backgroundColor = 0xFF0E141B,
                borderColor = 0x4DCA9D4B
            )
            .align(Alignment.TopStart)
            .clickable { onBackPressed() },
        contentAlignment = contentAlignment
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun BoxScope.BackButtonPreview() {
    BackButton(Modifier)
}