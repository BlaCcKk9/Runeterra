package com.lms.worldoflol.ui

import com.lms.worldoflol.R

sealed class BottomBarScreen(
    val route: String,
    val icon: Int,
    val icon_focused: Int
) {
    object Profile: BottomBarScreen(
        route = "profile",
        icon = R.drawable.ic_profile,
        icon_focused = R.drawable.ic_profile_focused
    )

    object Search: BottomBarScreen(
        route = "search",
        icon = R.drawable.ic_search,
        icon_focused = R.drawable.ic_search_focused
    )

    object Champions: BottomBarScreen(
        route = "champions",
        icon = R.drawable.ic_champion,
        icon_focused = R.drawable.ic_champion_focused
    )
}
