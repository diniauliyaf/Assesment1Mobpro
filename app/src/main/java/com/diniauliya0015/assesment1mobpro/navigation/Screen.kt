package com.diniauliya0015.assesment1mobpro.navigation

sealed class Screen (val route: String){
    data object Home: Screen("mainScreen")
}