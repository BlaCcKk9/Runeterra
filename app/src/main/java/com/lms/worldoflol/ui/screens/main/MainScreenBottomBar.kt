package com.lms.worldoflol.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.BottomBarScreen
import com.lms.worldoflol.utils.bottomBarNavigate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

@Composable
fun MainScreenBottomBar(
    navController: NavHostController,
) {
    val screens = listOf(
        BottomBarScreen.Profile,
        BottomBarScreen.Search,
        BottomBarScreen.Champions,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    BottomBarContent {
        val scope = rememberCoroutineScope()
        screens.forEach { screen ->
            it.AddItem(
                screen = screen,
                scope = scope,
                bottomBarDestination = bottomBarDestination,
                currentDestination = currentDestination,
                navController = navController,
            )
        }
    }
}

@Composable
fun BottomBarContent(
    content: @Composable (scope: RowScope) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 5.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_bbar),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            content.invoke(this)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    scope: CoroutineScope,
    bottomBarDestination: Boolean,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    var waitEndAnimationJob by remember { mutableStateOf<Job?>(null) }
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val icon = if (selected) screen.icon_focused else screen.icon
    val itemBottomPadding = if (screen.route == "search") 23.dp else 17.dp

    Box(
        modifier = Modifier
            .wrapContentSize()
            .drawBehind {
                if (selected) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0x33EEE2CC),
                                Color(0x00EEE2CC)
                            )
                        ),
                        radius = if (screen.route == "search") 120.dp.toPx() else 104.dp.toPx(),
                    )
                }
            }
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                bottomBarNavigate(
                    route = screen.route,
                    bottomBarDestination = bottomBarDestination,
                    scope = scope,
                    navController = navController,
                    wait = waitEndAnimationJob,
                    onAnimStart = { waitEndAnimationJob = it },
                    onAnimCancel = { waitEndAnimationJob = null }
                )
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomBarItemIndicator(
            visible = selected,
            rowScope = this@AddItem
        )
        Image(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = itemBottomPadding
                )
                .size(33.dp),
            painter = painterResource(id = icon),
            contentScale = ContentScale.Crop,
            contentDescription = "Navigation Icon",
        )
    }
}

@Composable
fun BoxScope.BottomBarItemIndicator(visible: Boolean, rowScope: RowScope) {
    rowScope.AnimatedVisibility(visible = visible) {
        BottomBarIndicator()
    }
}

@Composable
fun BoxScope.BottomBarIndicator(){
    Spacer(
        modifier = Modifier
            .padding(bottom = 14.dp)
            .width(33.dp)
            .height(1.dp)
            .background(color = Color(0xFFEEE2CC))
            .align(Alignment.BottomCenter)
    )
}

@Preview
@Composable
fun BottomBarPreview() {
    MainScreenBottomBar(rememberNavController())
}