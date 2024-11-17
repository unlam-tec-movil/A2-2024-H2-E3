package ar.edu.unlam.mobile.scaffolding.ui.screens

sealed class NavigationRoutes(val route: String){
    data object DraftListScreen: NavigationRoutes("draftList")
    data object CreateTuitScreen: NavigationRoutes("createTuit")
    data object ProfileScreen: NavigationRoutes("profile")
    data object HomeScreen: NavigationRoutes("home")
    data object RegisterScreen: NavigationRoutes("register")
    data object LoginScreen: NavigationRoutes("login")
}