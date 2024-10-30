package ar.edu.unlam.mobile.scaffolding.ui.screens.login

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
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
//    val loginState by remember { viewModel.loginState }
    val snackBarHostState = remember { SnackbarHostState() }
//
//    LaunchedEffect(Unit) {
//        viewModel.userEventsState.collectLatest { event ->
//            when(event){
//                is UserEvents.NavigateToRegister -> onNavigateToHomeScreen()
//                is UserEvents.NavigateToHome -> onNavigateToHomeScreen()
//                is UserEvents.ShowError -> snackBarHostState.showSnackbar(event.message)
//            }
//        }
//    }

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
                text = "¡Buenas tardes!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )
//            LoginInputs(
//                loginState = loginState,
//                onEmailChange = { inputString ->
//                    viewModel.onLoginEvent(
//                        event = LoginEvents.UpdateEmail(
//                            email = inputString
//                        )
//                    )
//                },
//                onPasswordChange = { inputString ->
//                    viewModel.onLoginEvent(
//                        event = LoginEvents.UpdatePassword(
//                            password = inputString
//                        )
//                    )
//                },
//                onSubmit = {
//                    viewModel.onLoginEvent(event = LoginEvents.LogUser)
//                }
//            )

        }
    }
}