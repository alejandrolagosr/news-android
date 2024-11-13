package com.flagos.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flagos.news.ui.screen.HomeScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(NavigationScreen.HomeScreen.route) {
            HomeScreen()
        }
    }
}