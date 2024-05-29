package com.enesbaskaya.mybank.ui.screen

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash")
    data object ListScreen : Screen("list")
    data object DetailScreen: Screen("detail")

    data object SettingScreen: Screen("setting")


}