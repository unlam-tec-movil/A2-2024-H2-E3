package ar.edu.unlam.mobile.scaffolding.ui.screens.login.state

data class LoginState(
    val emailTextField: String = "",
    val passwordTextField: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
)
data class LoginErrorState(
    val emailErrorState: Boolean = false,
    val passwordErrorState: Boolean = false,
)