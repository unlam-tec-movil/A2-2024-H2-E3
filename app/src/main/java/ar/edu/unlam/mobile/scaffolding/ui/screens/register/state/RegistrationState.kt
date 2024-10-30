package ar.edu.unlam.mobile.scaffolding.ui.screens.register.state

data class RegistrationState(
    val emailTextField: String = "",
    val usernameTextField: String = "",
    val passwordTextField: String = "",
    val confirmPasswordTextField: String = "",
    val errorState: RegistrationErrorState = RegistrationErrorState(),
)

data class RegistrationErrorState(
    val emailErrorState: Boolean = false,
    val usernameErrorState: Boolean = false,
    val passwordErrorState: Boolean = false,
    val confirmPasswordErrorState: Boolean = false,
)