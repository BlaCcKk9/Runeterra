package com.lms.worldoflol.ui.screens.main.profile.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.theme.textStyle16
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun ProfileMenu(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    onClose: () -> Unit,
    onRefresh: () -> Unit,
    onLogout: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .wrapContentHeight()
                .width(183.dp)
                .backgroundWithBorder(
                    backgroundColor = 0xFF0E141B,
                    borderGradientColors = arrayListOf(0x1ACA9D4B, 0x1AEEE2CC),
                    borderWidth = 3.dp
                ),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            ProifileMenuHeader { onClose() }
            ProfileMenuBody(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onLogout = { onLogout() },
                onRefresh = { onRefresh() }
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun ProifileMenuHeader(onClose: () -> Unit) {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_close_menu),
        tint = Color(0x80EEE2CC),
        contentDescription = "CLOSE",
        modifier = Modifier
            .padding(end = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClose() }
    )

    Spacer(modifier = Modifier.size(15.dp))
    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 0.5.dp,
        color = Color(0x1ACA9D4B)
    )
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
private fun ProfileMenuBody(
    modifier: Modifier,
    onLogout: () -> Unit,
    onRefresh: () -> Unit
) {
    ProfileMenuBodyItem(
        modifier = modifier.clickable { onLogout() },
        image = R.drawable.ic_logout,
        text = "Log Out"
    )

    Spacer(modifier = Modifier.size(20.dp))

    ProfileMenuBodyItem(
        modifier = modifier.clickable { onRefresh() },
        image = R.drawable.ic_refresh,
        text = "Refresh"
    )

}

@Composable
fun ProfileMenuBodyItem(
    modifier: Modifier,
    image: Int,
    text: String
) {
    Row(
        modifier = modifier.padding(start = 12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier.size(26.dp),
            painter = painterResource(id = image),
            contentScale = ContentScale.FillBounds,
            contentDescription = "ITEM_ICON",
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = textStyle16(color = 0xFFEEE2CC, fontStyle = FontStyle.Italic),
        )
    }
}
