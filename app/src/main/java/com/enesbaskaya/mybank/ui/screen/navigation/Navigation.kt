package com.enesbaskaya.mybank.ui.screen.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enesbaskaya.mybank.ui.screen.Screen
import com.enesbaskaya.mybank.ui.screen.detail.DetailScreen
import com.enesbaskaya.mybank.ui.screen.list.ListScreen
import com.enesbaskaya.mybank.ui.screen.setting.SettingScreen
import com.enesbaskaya.mybank.ui.screen.splash.SplashScreen


@Composable
fun SetUpNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(700)
            )
        }
    ) {
        appGraph(navController)
    }


}

private fun NavGraphBuilder.appGraph(navController: NavHostController) {
    composable(Screen.SplashScreen.route) { SplashScreen(navController) }
    composable(Screen.ListScreen.route) { ListScreen(navController) }
    composable(
        "${Screen.DetailScreen.route}/{bankDetailJson}",
        arguments = listOf(navArgument("bankDetailJson") {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val bankDataJson = backStackEntry.arguments?.getString("bankDetailJson")
        if (bankDataJson != null) {
            DetailScreen(navController, bankDataJson)
        }
    }
    composable(Screen.SettingScreen.route) { SettingScreen(navController) }

}
