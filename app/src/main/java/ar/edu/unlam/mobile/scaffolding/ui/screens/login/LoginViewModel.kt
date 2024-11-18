package ar.edu.unlam.mobile.scaffolding.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import ar.edu.unlam.mobile.scaffolding.ui.common.UserUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.event.LoginUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.state.LoginErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private var _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _userUiState = MutableSharedFlow<UserUiEvent>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val userUiState: SharedFlow<UserUiEvent> = _userUiState.asSharedFlow()

    fun isUserLogged() {
        viewModelScope.launch {
            if (userRepository.isUserLogged()) {
                emitUserEvent(event = UserUiEvent.NavigateToHomeScreen)
            }
        }
    }

    fun onLoginEvent(event: LoginUiEvent) {
        viewModelScope.launch {
            when (event) {
                is LoginUiEvent.UpdateEmail -> {
                    _loginState.value = loginState.value.copy(
                        emailTextField = event.email.trim(),
                        errorState = loginState.value.errorState.copy(
                            emailErrorState = loginState.value.emailTextField.isEmpty()
                        )
                    )
                }

                is LoginUiEvent.UpdatePassword -> {
                    _loginState.value = loginState.value.copy(
                        passwordTextField = event.password.trim(),
                        errorState = loginState.value.errorState.copy(
                            passwordErrorState = loginState.value.passwordTextField.isEmpty()
                        )
                    )
                }

                is LoginUiEvent.RegisterTextClicked -> {
                    emitUserEvent(event = UserUiEvent.NavigateToRegisterScreen)
                }

                is LoginUiEvent.Submit -> {
                    if (areAnyFieldEmpty()) {
                        try {
                            userRepository.login(
                                _loginState.value.emailTextField,
                                _loginState.value.passwordTextField
                            )
                            emitUserEvent(event = UserUiEvent.NavigateToHomeScreen)
                        } catch (e: Exception) {
                            emitUserEvent(event = UserUiEvent.ShowError("${e.message} Error: Email o contraseña incorrecto"))
                        }
                    } else {
                        emitUserEvent(event = UserUiEvent.ShowError("Por favor complete los campos vacios"))
                    }
                }
            }
        }
    }

    private fun areAnyFieldEmpty(): Boolean {
        val emailString = loginState.value.emailTextField
        val passwordString = loginState.value.passwordTextField

        return when {
            emailString.isEmpty() -> {
                _loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(emailErrorState = true)
                )
                false
            }

            passwordString.isEmpty() -> {
                _loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(passwordErrorState = true)
                )
                false
            }

            // sin errores
            else -> {
                // default error state
                _loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }

    private suspend fun emitUserEvent(event: UserUiEvent) = _userUiState.emit(event)
}
