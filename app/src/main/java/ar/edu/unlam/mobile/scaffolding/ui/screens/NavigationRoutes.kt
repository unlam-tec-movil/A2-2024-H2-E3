package ar.edu.unlam.mobile.scaffolding.ui.screens

sealed class NavigationRoutes(val route: String){
    data object HomeScreen: NavigationRoutes("home")
    data object RegisterScreen: NavigationRoutes("register")
//    object LogingScreen: NavigationRoutes("login")
}