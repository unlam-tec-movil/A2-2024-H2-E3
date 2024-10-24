package ar.edu.unlam.mobile.scaffolding.ui.screens.register.event

sealed class RegisterEvents {
    class UpdateEmail(val email: String): RegisterEvents()
    class UpdateUsername(val username: String): RegisterEvents()
    class UpdatePassword(val password: String): RegisterEvents()
    class UpdateConfirmPassword(val password: String): RegisterEvents()
    data object RegisterUser: RegisterEvents()
}