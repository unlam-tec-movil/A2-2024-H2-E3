package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.unlam.mobile.scaffolding.ui.screens.NavigationRoutes


@Composable
fun BottomBar(controller: NavHostController) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == NavigationRoutes.HomeScreen.route } == true,
            onClick = {
                // Navega solo si no estás ya en "home"
                if (currentDestination?.route != NavigationRoutes.HomeScreen.route) {
                    controller.navigate(NavigationRoutes.HomeScreen.route) {
                        popUpTo(NavigationRoutes.HomeScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == NavigationRoutes.ProfileScreen.route } == true,
            onClick = {
                // Navega solo si no estás ya en "ProfileScreen"
                if (currentDestination?.route != NavigationRoutes.ProfileScreen.route) {
                    controller.navigate(NavigationRoutes.ProfileScreen.route) {
                        popUpTo(NavigationRoutes.HomeScreen.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
    }
}
