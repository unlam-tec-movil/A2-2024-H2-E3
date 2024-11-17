package ar.edu.unlam.mobile.scaffolding.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.event.RegistrationUiEvent
import ar.edu.unlam.mobile.scaffolding.ui.common.UserUiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    onNavigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerState by remember { viewModel.registrationState }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.userUiState.collectLatest { event ->
            when (event) {
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
                text = "Registro",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            RegistrationInputs(registrationState = registerState,
                onEmailChange = { inputString ->
                    viewModel.onRegistrationUiEvent(
                        event = RegistrationUiEvent.UpdateEmail(
                            email = inputString
                        )
                    )
                },
                onUsernameChange = { inputString ->
                    viewModel.onRegistrationUiEvent(
                        event = RegistrationUiEvent.UpdateUsername(
                            username = inputString
                        )
                    )
                },
                onPasswordChange = { inputString ->
                    viewModel.onRegistrationUiEvent(
                        event = RegistrationUiEvent.UpdatePassword(
                            password = inputString
                        )
                    )
                },
                onConfirmPasswordChange = { inputString ->
                    viewModel.onRegistrationUiEvent(
                        event = RegistrationUiEvent.UpdateConfirmPassword(
                            password = inputString
                        )
                    )
                },
                onSubmit = {
                    viewModel.onRegistrationUiEvent(event = RegistrationUiEvent.Submit)
                })

        }
    }
}