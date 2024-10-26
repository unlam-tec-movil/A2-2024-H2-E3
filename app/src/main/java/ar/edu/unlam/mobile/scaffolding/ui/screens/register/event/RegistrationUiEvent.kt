package ar.edu.unlam.mobile.scaffolding.ui.screens.register.event

sealed class RegistrationUiEvent {
    data class UpdateEmail(val email: String): RegistrationUiEvent()
    data class UpdateUsername(val username: String): RegistrationUiEvent()
    data class UpdatePassword(val password: String): RegistrationUiEvent()
    data class UpdateConfirmPassword(val password: String): RegistrationUiEvent()
    data object Submit: RegistrationUiEvent()
}