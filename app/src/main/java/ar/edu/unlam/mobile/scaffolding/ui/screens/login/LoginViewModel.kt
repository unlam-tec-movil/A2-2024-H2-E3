package ar.edu.unlam.mobile.scaffolding.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.event.LoginUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.state.LoginState
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.state.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private var _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

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
//                emitUserEvent(event = UserUiEvent.NavigateToRegisterScreen)
            }

            is LoginUiEvent.Submit -> {
//                if(areAnyFieldEmpty()){
//                    emitUserEvent(event = UserUiEvent.NavigateToHomeScreen)
//                }else{
//                    emitUserEvent(event = UserUiEvent.ShowError("Por favor complete los campos vacios"))
//                }
            }
        }
    }
}
