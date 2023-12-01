package com.dicoding.the10wonders.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import com.dicoding.the10wonders.presentation.navigation.BottomNavigationItem
import com.dicoding.the10wonders.presentation.navigation.Screen
import com.dicoding.the10wonders.presentation.screen.about.AboutScreen
import com.dicoding.the10wonders.presentation.screen.detail.DetailScreen
import com.dicoding.the10wonders.presentation.screen.home.HomeScreen
import com.dicoding.the10wonders.presentation.screen.splash.SplashScreen

@Composable
fun The10WondersApp(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute.shouldShowBottomBar()) {
                BottomBar(navController = navController)
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onTimeOut = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("id", 0)

                DetailScreen(
                    id = id,
                    navController = navController,
                )
            }
            composable(Screen.About.route) {
                AboutScreen(navController = navController)
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            screen = Screen.Home,
            activeIcon = Icons.Rounded.Home,
            inactiveIcon = Icons.Outlined.Home,
            contentDescription = "home_page"
        ),
        BottomNavigationItem(
            title = "About",
            screen = Screen.About,
            activeIcon = Icons.Rounded.Person,
            inactiveIcon = Icons.Outlined.Person,
            contentDescription = "about_page"
        )
    )

    NavigationBar(modifier = modifier) {
        items.map { item ->
            NavigationBarItem(
                selected = item.screen.route == currentRoute,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph[Screen.Home.route].id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (item.screen.route == currentRoute) item.activeIcon else item.inactiveIcon,
                        contentDescription = item.contentDescription
                    )
                },
                label = { Text(text = item.title) }
            )
        }
    }
}

private fun String?.shouldShowBottomBar(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.About.route,
    )
}