package ar.edu.unlam.mobile.scaffolding.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.common.UserUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.screens.login.event.LoginUiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by remember { viewModel.loginState }
    val snackBarHostState = remember { SnackbarHostState() }

    viewModel.isUserLogged()

    LaunchedEffect(Unit) {
        viewModel.userUiState.collectLatest { event ->
            when (event) {
                is UserUiEvent.NavigateToRegisterScreen -> onNavigateToRegisterScreen()
                is UserUiEvent.NavigateToHomeScreen -> onNavigateToHomeScreen()
                is UserUiEvent.ShowError -> snackBarHostState.showSnackbar(event.message)
                else -> Unit
            }
        }
    }

    Scaffold(modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.welcome_text), style = TextStyle(
                    fontSize = 40.sp,
                    color = Color.Blue,
                    letterSpacing = 2.sp,
                ), modifier = Modifier.padding(bottom = 32.dp)
            )
            LoginInputs(loginState = loginState, onEmailChange = { inputString ->
                viewModel.onLoginEvent(
                    event = LoginUiEvent.UpdateEmail(
                        email = inputString
                    )
                )
            }, onPasswordChange = { inputString ->
                viewModel.onLoginEvent(
                    event = LoginUiEvent.UpdatePassword(
                        password = inputString
                    )
                )
            }, onRegisterTextClicked = {
                viewModel.onLoginEvent(event = LoginUiEvent.RegisterTextClicked)
            }, onSubmit = {
                viewModel.onLoginEvent(event = LoginUiEvent.Submit)
            })
        }
    }
}
