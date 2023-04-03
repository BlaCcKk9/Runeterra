package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.theme.QuadrataFamily
import com.lms.worldoflol.ui.theme.textFieldHintColor
import com.lms.worldoflol.ui.theme.textStyle10
import com.lms.worldoflol.ui.theme.textStyle20
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun SummonerNameTextField(
    summonerName: TextFieldValue,
    isAccountNotFound: () -> Boolean,
    onSummonerNameChanged: (name: TextFieldValue) -> Unit,
) {

    Column {
        TextField(
            value = summonerName,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color(0xFFCA9D4B),
                cursorColor = Color(0xFFCA9D4B)
            ),
            singleLine = true,
            textStyle = textStyle20(color = 0xFF242731, textAlign = TextAlign.Start),
            isError = isAccountNotFound(),
            onValueChange = { onSummonerNameChanged(it) },
            trailingIcon = { if (isAccountNotFound()) TralingIcon() },
            placeholder = { SummonerNameHint() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .backgroundWithBorder(
                    backgroundColor = 0xFFEEE2CC,
                    borderColor = 0x99242731
                )
                .clickable(
                    onClick = { }
                )
        )
        AnimatedVisibility(visible = isAccountNotFound()) {
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                ErrorText("Summoner name don't match")
            }
        }
    }
}

@Composable
fun SummonerNameHint() {
    Text(
        text = "Summoner Name",
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 23.dp),
        textAlign = TextAlign.Start,
        fontFamily = QuadrataFamily,
        fontWeight = FontWeight(400),
        fontSize = 18.sp,
        color = textFieldHintColor
    )
}

@Composable
fun TralingIcon() {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_account_not_found),
        tint = Color(0xFFCA4B4B),
        contentDescription = ""
    )
}

@Composable
fun ErrorText(text: String) {
    Row(
        modifier = Modifier.padding(start = 5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_warning),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = textStyle10(color = 0xFFCA4B4B)
        )
    }
}

@Preview
@Composable
fun SummonerNameTextFieldPreview() {
    SummonerNameTextField(
        summonerName = TextFieldValue("i am high"),
        isAccountNotFound = { true }
    ) {}
}





