package com.lms.worldoflol.ui.screens.main

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.ScrollCaptureCallback
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.graphs.MainNavGraph
import kotlin.math.roundToInt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberAnimatedNavController(),
    summoner: Summoner?,
) {
    var showBottomBar by remember { mutableStateOf(false) }
    var bottomBarAvailable by remember { mutableStateOf(true) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if (bottomBarAvailable) showBottomBar = consumed.y >= 0
                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        bottomBar = {
            BottomBarContent(
                visible = showBottomBar,
                navController = navController
            )
        }) {
        MainNavGraph(
            navHostController = navController,
            summoner = summoner
        ) { isVisible ->
            showBottomBar = isVisible
            bottomBarAvailable = isVisible
        }
    }
}

@Composable
fun BottomBarContent(
    visible: Boolean,
    navController: NavHostController
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(spring(stiffness = Spring.StiffnessMedium)) { it } + fadeIn(),
        exit = slideOutVertically(spring(stiffness = Spring.StiffnessMedium)) { it } + fadeOut()
    ) {
        MainScreenBottomBar(navController = navController)
    }
}


