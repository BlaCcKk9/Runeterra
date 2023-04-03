package com.lms.worldoflol.ui.screens.main.profile.all_games.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.theme.textStyle

@Composable
fun FilterButtonContainer(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    val strokeColor = if (isSelected) Color(0xFFCA9D4B) else Color(0x4DCA9D4B)
    val background = if (isSelected) Color(0xCC0E141B) else Color(0xB30E141B)
    val textColor: Long = if (isSelected) 0xB3EEE2CC else 0x4DEEE2CC

    Button(
        onClick = { onClick() },
        enabled = true,
        elevation = null,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, strokeColor),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background
        ),
        contentPadding = PaddingValues(horizontal = 0.dp),
        modifier = Modifier
            .height(37.dp)
            .wrapContentWidth()
    ) {
        Text(
            text = text,
            style = textStyle(
                size = 14.sp,
                color = textColor
            ),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
fun FilterButtonContainer(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    val strokeColor = if (isSelected) Color(0xFFCA9D4B) else Color(0x4DCA9D4B)
    val background = if (isSelected) Color(0xCC0E141B) else Color(0xB30E141B)
    val textColor: Long = if (isSelected) 0xB3EEE2CC else 0x4DEEE2CC

    Button(
        onClick = { onClick() },
        enabled = true,
        elevation = null,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, strokeColor),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background
        ),
        contentPadding = PaddingValues(horizontal = 0.dp),
        modifier = modifier
            .height(37.dp)
            .wrapContentWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .wrapContentWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filter_date),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Date",
                style = textStyle(
                    size = 14.sp,
                    color = textColor
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_bottom),
                contentDescription = ""
            )
        }
    }
}