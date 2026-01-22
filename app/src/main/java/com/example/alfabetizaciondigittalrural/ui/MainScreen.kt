package com.example.alfabetizaciondigittalrural.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.alfabetizaciondigittalrural.data.repository.UserRepository
import com.example.alfabetizaciondigittalrural.ui.dashboard.DashboardScreen
import com.example.alfabetizaciondigittalrural.ui.lessons.LessonsScreen
import com.example.alfabetizaciondigittalrural.ui.navigation.Screen
import com.example.alfabetizaciondigittalrural.ui.navigation.bottomNavItems
import com.example.alfabetizaciondigittalrural.ui.profile.ProfileScreen

@Composable
fun MainScreen(
    appContainer: com.example.alfabetizaciondigittalrural.AppContainer
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                DashboardScreen(userRepository = appContainer.userRepository)
            }
            composable(Screen.Lessons.route) {
                LessonsScreen(educationRepository = appContainer.educationRepository)
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        bottomNavItems.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                label = { Text(screen.title) },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
