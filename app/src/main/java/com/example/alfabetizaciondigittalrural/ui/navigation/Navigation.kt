package com.example.alfabetizaciondigittalrural.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Inicio", Icons.Filled.Home)
    object Lessons : Screen("lessons", "Lecciones", Icons.Filled.List)
    object Profile : Screen("profile", "Perfil", Icons.Filled.Person)
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Lessons,
    Screen.Profile
)
