package com.diniauliya0015.assesment1mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diniauliya0015.assesment1mobpro.ui.screen.AboutScreen
import com.diniauliya0015.assesment1mobpro.ui.screen.FormulaInfoScreen
import com.diniauliya0015.assesment1mobpro.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Rumus.route) {
            FormulaInfoScreen(navController)
        }
    }
}