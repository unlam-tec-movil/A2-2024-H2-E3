package ar.edu.unlam.mobile.scaffolding.ui.screens.register.event

sealed class UserUiEvent {
    data object NavigateToHomeScreen : UserUiEvent()
    data class ShowError(val message: String) : UserUiEvent()
}