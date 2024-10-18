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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.register.event.RegisterEvents
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier, viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by remember { viewModel.registerState }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.userEventsState.collectLatest {
            if (it.snackbarMessage.isNotBlank()) {
                snackBarHostState.showSnackbar(message = it.snackbarMessage)
            }
        }
    }

    Scaffold(modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) } ){ padding ->
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
            RegistrationInputs(
                registrationState = state,
                onEmailChange = { inputString ->
                    viewModel.onRegistrationEvent(
                        event = RegisterEvents.UpdateEmail(
                            email = inputString
                        )
                    )
                },
                onUsernameChange = { inputString ->
                    viewModel.onRegistrationEvent(
                        event = RegisterEvents.UpdateUsername(
                            username = inputString
                        )
                    )
                },
                onPasswordChange = { inputString ->
                    viewModel.onRegistrationEvent(
                        event = RegisterEvents.UpdatePassword(
                            password = inputString
                        )
                    )
                },
                onConfirmPasswordChange = { inputString ->
                    viewModel.onRegistrationEvent(
                        event = RegisterEvents.UpdateConfirmPassword(
                            password = inputString
                        )
                    )
                },
                onSubmit = {
                    viewModel.onRegistrationEvent(event = RegisterEvents.RegisterUser)
                }
            )

        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}