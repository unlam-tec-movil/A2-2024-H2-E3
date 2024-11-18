package ar.edu.unlam.mobile.scaffolding.ui.screens.register


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.event.RegistrationUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.common.UserUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _registrationState = mutableStateOf(RegistrationState())
    val registrationState: State<RegistrationState> = _registrationState

    private val _userUiState = MutableSharedFlow<UserUiEvent>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val userUiState: SharedFlow<UserUiEvent> = _userUiState.asSharedFlow()

    fun onRegistrationUiEvent(event: RegistrationUiEvent) {
        when (event) {
            is RegistrationUiEvent.UpdateEmail -> {
                _registrationState.value = registrationState.value.copy(
                    emailTextField = event.email.trim(),
                    errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.emailTextField.isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.UpdateUsername -> {
                _registrationState.value = registrationState.value.copy(
                    usernameTextField = event.username.trim(),
                    errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.usernameTextField.isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.UpdatePassword -> {
                _registrationState.value = registrationState.value.copy(
                    passwordTextField = event.password.trim(),
                    errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.passwordTextField.isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.UpdateConfirmPassword -> {
                _registrationState.value = registrationState.value.copy(
                    confirmPasswordTextField = event.password.trim(),
                    errorState = registrationState.value.errorState.copy(
                        emailErrorState = registrationState.value.confirmPasswordTextField.isEmpty()
                    )
                )
            }

            is RegistrationUiEvent.Submit -> {
                viewModelScope.launch {
                    if (areAnyFieldEmpty()) {
                        if (isConfirmPasswordCorrect()) {
                            try {
                                userRepository.register(
                                    User(
                                        email = _registrationState.value.emailTextField,
                                        name = _registrationState.value.usernameTextField,
                                        password = _registrationState.value.passwordTextField
                                    )
                                )
                                emitUserEvent(event = UserUiEvent.NavigateToHomeScreen)
                            } catch (e: Exception) {
                                emitUserEvent(event = UserUiEvent.ShowError("Por favor ingrese un email valido"))
                            }
                        } else {
                            emitUserEvent(event = UserUiEvent.ShowError("Las contraseñas no coinciden"))
                        }
                    } else emitUserEvent(event = UserUiEvent.ShowError("Por favor complete los campos vacios"))
                }
            }
        }
    }

    // TODO se podria mover la validacion de datos a la capa de domain
    private fun isConfirmPasswordCorrect(): Boolean {
        val passwordString = registrationState.value.passwordTextField.trim()
        val confirmPasswordString = registrationState.value.confirmPasswordTextField.trim()

        if (passwordString != confirmPasswordString) {
            _registrationState.value = registrationState.value.copy(
                errorState = RegistrationErrorState(confirmPasswordErrorState = true)
            )
            return false
        }
        return true
    }

    private fun areAnyFieldEmpty(): Boolean {
        val emailString = registrationState.value.emailTextField
        val usernameString = registrationState.value.usernameTextField
        val passwordString = registrationState.value.passwordTextField
        val confirmPasswordString = registrationState.value.confirmPasswordTextField

        return when {

            emailString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(emailErrorState = true)
                )
                false
            }

            usernameString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(usernameErrorState = true)
                )
                false
            }

            passwordString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(passwordErrorState = true)
                )
                false
            }

            confirmPasswordString.isEmpty() -> {
                _registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(confirmPasswordErrorState = true)
                )
                false
            }

            // sin errores
            else -> {
                // default error state
                _registrationState.value =
                    registrationState.value.copy(errorState = RegistrationErrorState())
                true
            }
        }
    }

    private suspend fun emitUserEvent(event: UserUiEvent) = _userUiState.emit(event)
}