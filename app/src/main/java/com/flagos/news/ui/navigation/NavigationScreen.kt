package com.flagos.news.ui.navigation

sealed class NavigationScreen(val route: String) {

    object HomeScreen : NavigationScreen(
        route = "home_screen"
    )
}