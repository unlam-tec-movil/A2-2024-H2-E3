package ar.edu.unlam.mobile.scaffolding.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.ui.common.UserUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.event.LoginUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.state.LoginErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.state.LoginState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationErrorState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private var _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _userUiState = MutableSharedFlow<UserUiEvent>()
    val UserUiState: SharedFlow<UserUiEvent> = _userUiState.asSharedFlow()

    fun onLoginEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.UpdateEmail -> {
                _loginState.value = loginState.value.copy(
                    emailTextField = event.email, errorState = loginState.value.errorState.copy(
                        emailErrorState = loginState.value.emailTextField.trim().isEmpty()
                    )
                )
            }

            is LoginUiEvent.UpdatePassword -> {
                _loginState.value = loginState.value.copy(
                    passwordTextField = event.password,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState = loginState.value.passwordTextField.trim().isEmpty()
                    )
                )
            }

            is LoginUiEvent.RegisterTextClicked -> {
                emitUserEvent(event = UserUiEvent.NavigateToRegisterScreen)
            }

            is LoginUiEvent.Submit -> {
                if(areAnyFieldEmpty()){
                    emitUserEvent(event = UserUiEvent.NavigateToHomeScreen)
                }else{
                    emitUserEvent(event = UserUiEvent.ShowError("Por favor complete los campos vacios"))
                }
            }
        }
    }

    private fun areAnyFieldEmpty(): Boolean {
        val emailString = loginState.value.emailTextField.trim()
        val passwordString = loginState.value.passwordTextField.trim()

        return when {
            emailString.isEmpty() -> {
                _loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(emailErrorState = true)
                )
                return false
            }

            passwordString.isEmpty() -> {
                _loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(passwordErrorState = true)
                )
                return false
            }

            // sin errores
            else -> {
                // default error state
                _loginState.value =
                    loginState.value.copy(errorState = LoginErrorState())
                return true
            }
        }
    }

    private fun emitUserEvent(event: UserUiEvent) = viewModelScope.launch {
        when (event) {
            is UserUiEvent.NavigateToHomeScreen -> _userUiState.emit(UserUiEvent.NavigateToHomeScreen)
            is UserUiEvent.ShowError -> _userUiState.emit(UserUiEvent.ShowError(event.message))
            is UserUiEvent.NavigateToRegisterScreen -> _userUiState.emit(UserUiEvent.NavigateToRegisterScreen)
            else -> Unit
        }
    }
}
