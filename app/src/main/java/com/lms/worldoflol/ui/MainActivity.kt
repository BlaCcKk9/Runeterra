@file:OptIn(ExperimentalAnimationApi::class)

package com.lms.worldoflol.ui

import android.content.Context.WINDOW_SERVICE
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lms.worldoflol.ui.graphs.RootNavigationGraph
import com.lms.worldoflol.ui.theme.WorldOfLoLTheme
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiController = rememberSystemUiController()
            uiController.isStatusBarVisible = false
            
            WorldOfLoLTheme {
                RootNavigationGraph(navController = rememberAnimatedNavController())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorldOfLoLTheme {
        RootNavigationGraph(rememberNavController())
    }
}