package com.dicoding.the10wonders.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    object About : Screen("about")
}