package ar.edu.unlam.mobile.scaffolding.ui.screens.register


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.ui.common.ErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.event.RegistrationUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegisterState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.confirmPasswordEmptyErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.emailEmptyErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.passwordEmptyErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.passwordMismatchErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.usernameEmptyErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    fun onRegistrationEvent(event: RegistrationUiEvent) {
        when (event) {
            is RegistrationUiEvent.UpdateEmail -> {
                _registerState.value = registerState.value.copy(
                    emailTextField = event.email,
                    errorState = registerState.value.errorState.copy(
                        emailErrorState = if (registerState.value.emailTextField.trim().isEmpty()) {
                            // el campo esta vacio
                            emailEmptyErrorState
                        } else {
                            // no hay error
                            ErrorState()
                        }

                    )
                )
            }

            is RegistrationUiEvent.UpdateUsername -> {
                _registerState.value = registerState.value.copy(
                    usernameTextField = event.username,
                    errorState = registerState.value.errorState.copy(
                        emailErrorState = if (registerState.value.usernameTextField.trim().isEmpty()) {
                            usernameEmptyErrorState
                        } else {
                            ErrorState()
                        }

                    )
                )
            }

            is RegistrationUiEvent.UpdatePassword -> {
                _registerState.value = registerState.value.copy(
                    passwordTextField = event.password,
                    errorState = registerState.value.errorState.copy(
                        emailErrorState = if (registerState.value.passwordTextField.trim().isEmpty()) {
                            passwordEmptyErrorState
                        } else {
                            ErrorState()
                        }

                    )
                )
            }

            is RegistrationUiEvent.UpdateConfirmPassword -> {
                _registerState.value = registerState.value.copy(
                    confirmPasswordTextField = event.password,
                    errorState = registerState.value.errorState.copy(
                        emailErrorState = if (registerState.value.confirmPasswordTextField.trim().isEmpty()) {
                            confirmPasswordEmptyErrorState
                        } else {
                            ErrorState()
                        }

                    )
                )
            }

            is RegistrationUiEvent.Submit -> {
                val inputsValidated = validateFields()
                if (inputsValidated) {
                    _registerState.value =
                        registerState.value.copy(isRegistrationSuccessful = true)
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        val emailString = registerState.value.emailTextField.trim()
        val usernameString = registerState.value.usernameTextField.trim()
        val passwordString = registerState.value.passwordTextField.trim()
        val confirmPasswordString = registerState.value.confirmPasswordTextField.trim()

        return when {

            emailString.isEmpty() -> {
                _registerState.value = registerState.value.copy(
                    errorState = RegistrationErrorState(
                        emailErrorState = emailEmptyErrorState
                    )
                )
                false
            }

            usernameString.isEmpty() -> {
                _registerState.value = registerState.value.copy(
                    errorState = RegistrationErrorState(
                        usernameErrorState = usernameEmptyErrorState
                    )
                )
                false
            }

            passwordString.isEmpty() -> {
                _registerState.value = registerState.value.copy(
                    errorState = RegistrationErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            confirmPasswordString.isEmpty() -> {
                _registerState.value = registerState.value.copy(
                    errorState = RegistrationErrorState(
                        confirmPasswordErrorState = confirmPasswordEmptyErrorState
                    )
                )
                false
            }

            passwordString != confirmPasswordString -> {
                _registerState.value = registerState.value.copy(
                    errorState = RegistrationErrorState(
                        confirmPasswordErrorState = passwordMismatchErrorState
                    )
                )
                false
            }

            // sin errores
            else -> {
                // default error state
                _registerState.value =
                    registerState.value.copy(errorState = RegistrationErrorState())
                true
            }
        }
}
}