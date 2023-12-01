package com.dicoding.the10wonders.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val screen: Screen,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
    val contentDescription: String? = null,
)