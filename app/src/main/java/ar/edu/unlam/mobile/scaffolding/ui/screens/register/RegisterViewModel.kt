package ar.edu.unlam.mobile.scaffolding.ui.screens.register


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.event.RegistrationUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.common.UserUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO ADD USER REPOSITORY
@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registrationState = mutableStateOf(RegistrationState())
    val registrationState: State<RegistrationState> = _registrationState

    private val _userUiState = MutableSharedFlow<UserUiEvent>()
    val UserUiState: SharedFlow<UserUiEvent> = _userUiState.asSharedFlow()

    fun onRegistrationUiEvent(event: RegistrationUiEvent) {
        when (event) {
            is RegistrationUiEvent.UpdateEmail -> {
                _registrationState.value = registrationState.value.copy(
                    emailTextField = event.email, errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.emailTextField.trim().isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.UpdateUsername -> {
                _registrationState.value = registrationState.value.copy(
                    usernameTextField = event.username,
                    errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.usernameTextField.trim().isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.UpdatePassword -> {
                _registrationState.value = registrationState.value.copy(
                    passwordTextField = event.password,
                    errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.passwordTextField.trim().isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.UpdateConfirmPassword -> {
                _registrationState.value = registrationState.value.copy(
                    confirmPasswordTextField = event.password,
                    errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.confirmPasswordTextField.trim().isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.Submit -> {
                if (areAnyFieldEmpty()) {
                    if(isConfirmPasswordCorrect()){
                        emitUserEvent(event = UserUiEvent.NavigateToHomeScreen)
                    }else{
                        emitUserEvent(event = UserUiEvent.ShowError("Las contraseñas no coinciden"))
                    }
                }else
                    emitUserEvent(event = UserUiEvent.ShowError("Por favor complete los campos vacios"))

            }
        }
    }

    // TODO se podria mover la validacion de datos a la capa de domain
    private fun isConfirmPasswordCorrect(): Boolean {
        val passwordString = registrationState.value.passwordTextField.trim()
        val confirmPasswordString = registrationState.value.confirmPasswordTextField.trim()

        if(passwordString != confirmPasswordString){
            _registrationState.value = registrationState.value.copy(
                errorState = RegistrationErrorState(confirmPasswordErrorState = true))
            return false
        }
            return true
    }

    private fun areAnyFieldEmpty(): Boolean {
        val emailString = registrationState.value.emailTextField.trim()
        val usernameString = registrationState.value.usernameTextField.trim()
        val passwordString = registrationState.value.passwordTextField.trim()
        val confirmPasswordString = registrationState.value.confirmPasswordTextField.trim()

        return when {

            emailString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(emailErrorState = true))
                return false
            }

            usernameString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(usernameErrorState = true))
                return false
            }

            passwordString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(passwordErrorState = true))
                return false
            }

            confirmPasswordString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(confirmPasswordErrorState = true))
                return false
            }

            // sin errores
            else -> {
                // default error state
                _registrationState.value =
                    registrationState.value.copy(errorState = RegistrationErrorState())
                return true
            }
        }
    }

    private fun emitUserEvent(event: UserUiEvent) = viewModelScope.launch {
        when (event) {
            is UserUiEvent.NavigateToHomeScreen -> _userUiState.emit(UserUiEvent.NavigateToHomeScreen)
            is UserUiEvent.ShowError -> _userUiState.emit(UserUiEvent.ShowError(event.message))
        }
    }
}