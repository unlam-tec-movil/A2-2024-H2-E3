package ar.edu.unlam.mobile.scaffolding.ui.common

sealed class UserUiEvent {
    data object NavigateToRegisterScreen : UserUiEvent()
    data object NavigateToLoginScreen : UserUiEvent()
    data object NavigateToHomeScreen : UserUiEvent()
    data class ShowError(val message: String) : UserUiEvent()
}