package com.lms.worldoflol.ui.params

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.lms.worldoflol.common.Param

val profileArguments = listOf(
    navArgument(Param.SUMMONER_ID) {
        type = NavType.StringType
    },
    navArgument(Param.REGION) {
        type = NavType.StringType
    }
)

sealed class Arguments(val arguments: List<NamedNavArgument>) {
    object Profile : Arguments(profileArguments)
}