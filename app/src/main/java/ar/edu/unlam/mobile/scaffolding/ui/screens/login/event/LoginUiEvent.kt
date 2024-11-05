package ar.edu.unlam.mobile.scaffolding.ui.screens.login.event

sealed class LoginUiEvent{
    data class UpdateEmail(val email: String) : LoginUiEvent()
    data class UpdatePassword(val password: String) : LoginUiEvent()
    data object RegisterTextClicked: LoginUiEvent()
    data object Submit : LoginUiEvent()
}
