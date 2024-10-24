package ar.edu.unlam.mobile.scaffolding.ui.screens.register.event

sealed class UserEvents{
    data object NavigateToHome : UserEvents()
    data class ShowError(val message: String) : UserEvents()
}
