package ar.edu.unlam.mobile.scaffolding.ui.screens.register.state

import ar.edu.unlam.mobile.scaffolding.ui.common.ErrorState

data class RegisterState(
    val emailTextField: String = "",
    val usernameTextField: String = "",
    val passwordTextField: String = "",
    val confirmPasswordTextField: String = "",
    val errorState: RegistrationErrorState = RegistrationErrorState(),
    val isRegistrationSuccessful: Boolean = false,
)

data class RegistrationErrorState(
    val emailErrorState: ErrorState = ErrorState(),
    val usernameErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState()
)