package ar.edu.unlam.mobile.scaffolding.ui.screens.register


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.event.RegisterEvents
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.event.UserEvents
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    private val _userEventsState = MutableSharedFlow<UserEvents>()
    val userEventsState = _userEventsState.asSharedFlow()

    fun onRegistrationEvent(event: RegisterEvents) {
        when (event) {
            is RegisterEvents.UpdateEmail -> {
                _registerState.value = registerState.value.copy(
                    emailTextField = event.email
                )
            }
            is RegisterEvents.UpdateUsername -> {
                _registerState.value = registerState.value.copy(
                    usernameTextField = event.username
                )
            }
            is RegisterEvents.UpdatePassword -> {
                _registerState.value = registerState.value.copy(
                    passwordTextField = event.password
                )
            }
            is RegisterEvents.UpdateConfirmPassword -> {
                _registerState.value = registerState.value.copy(
                    confirmPasswordTextField = event.password
                )
            }

            RegisterEvents.RegisterUser -> {
                val state = _registerState.value
                val areFieldsValid = validateFields(state.emailTextField,state.usernameTextField, state.passwordTextField, state.confirmPasswordTextField)
                val isPasswordValid = validatePassword(state.passwordTextField, state.confirmPasswordTextField)

                if (!areFieldsValid) {
                    showSnackbar("Por favor complete los campos vacíos")
                } else if (!isPasswordValid) {
                    showSnackbar("La contraseña de verificación no coincide")
                } else {
                    registerUser()
                }
            }
        }
    }

    private fun validateFields(email: String,user: String, password: String, confirmPassword: String): Boolean {
        return !(email.isBlank() ||
                user.isBlank() ||
                password.isBlank() ||
                confirmPassword.isBlank())
    }

    private fun validatePassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    private fun registerUser() {
        // ToDo:: -Register- *1* / Priority: High
        // Description: Create user and navigate to HomeScreen
        showSnackbar("Usuario registrado correctamente")
    }

    private fun showSnackbar(msg: String) {
        viewModelScope.launch {
            _userEventsState.emit(
                UserEvents(snackbarMessage = msg)
            )
        }
    }

}