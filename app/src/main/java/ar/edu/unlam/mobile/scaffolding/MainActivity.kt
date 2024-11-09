package ar.edu.unlam.mobile.scaffolding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.components.BottomBar
import ar.edu.unlam.mobile.scaffolding.ui.screens.home.HomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.NavigationRoutes
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.LoginScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.RegisterScreen
import ar.edu.unlam.mobile.scaffolding.ui.theme.ScaffoldingV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldingV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen()
                }
            }
        }
    }
}
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        // Mostrar `BottomBar` solo si el destino actual es `HomeScreen`
        bottomBar = {
            if (currentDestination?.route == NavigationRoutes.HomeScreen.route) {
                BottomBar(controller = navController)
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValue ->
        // Configuración de NavHost para controlar las rutas de la app
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.LoginScreen.route
        ) {
            composable(NavigationRoutes.LoginScreen.route) {
                LoginScreen(
                    onNavigateToRegisterScreen = {
                        navController.navigate(NavigationRoutes.RegisterScreen.route)
                    },
                    onNavigateToHomeScreen = {
                        navController.navigate(NavigationRoutes.HomeScreen.route) {
                            popUpTo(NavigationRoutes.LoginScreen.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(paddingValue)
                )
            }

            composable(NavigationRoutes.RegisterScreen.route) {
                RegisterScreen(
                    onNavigateToHomeScreen = {
                        navController.navigate(NavigationRoutes.HomeScreen.route) {
                            popUpTo(NavigationRoutes.RegisterScreen.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(paddingValue)
                )
            }

            composable(NavigationRoutes.HomeScreen.route) {
                HomeScreen(
                    modifier = Modifier.padding(paddingValue),
                    onError = { message ->
                        LaunchedEffect(message) {
                            snackbarHostState.showSnackbar(
                                message = message,
                                actionLabel = "Retry"
                            )
                        }
                    },
                    navController = navController
                )
            }
        }
    }
}

