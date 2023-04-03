package com.lms.worldoflol.ui.screens.main.champions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.common.DropDown
import com.lms.worldoflol.common.MenuLazyColumn
import com.lms.worldoflol.common.Role
import com.lms.worldoflol.ui.theme.textStyle16

sealed class Roles(val name: String, val icon: Int) {
    object Assassin : Roles(Role.ASSASSIN, R.drawable.ic_assassin)
    object Fighter : Roles(Role.FIGHTER, R.drawable.ic_fighter)
    object Mage : Roles(Role.MAGE, R.drawable.ic_mage)
    object Marksman : Roles(Role.MARKSMAN, R.drawable.ic_marksman)
    object Support : Roles(Role.SUPPORT, R.drawable.ic_support)
    object Tank : Roles(Role.TANK, R.drawable.ic_tank)

    companion object {
        val roles = listOf(
            Assassin, Fighter, Mage, Marksman,
            Support, Tank
        )
    }
}


@Composable
fun RolesMenu(
    visible: Boolean = false,
    width: Dp,
    onRoleClick: (Roles) -> Unit
) {
    val roles = Roles.roles
    DropDown(
        initiallyOpened = visible,
        modifier = Modifier.padding(top = 120.dp, start = 16.dp)
    ) {
        MenuLazyColumn(width = width) {
            items(
                count = roles.size,
                key = { key -> key }
            ) { index ->
                RoleItem(role = roles[index]) {
                    onRoleClick(it)
                }
            }
        }
    }
}

@Composable
private fun RoleItem(
    role: Roles,
    onItemClick: (role: Roles) -> Unit
) {
    val roleName = role.name
    val roleIcon = role.icon

    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .clickable { onItemClick(role) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = roleIcon),
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Crop,
            contentDescription = "role icon"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = roleName,
            style = textStyle16(color = 0xFFEEE2CC, fontStyle = FontStyle.Italic),
        )
    }
}


