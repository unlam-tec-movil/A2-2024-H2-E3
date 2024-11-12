package ar.edu.unlam.mobile.scaffolding.ui.screens.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.domain.profile.models.Profile
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoadingScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.NavigationRoutes

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onError: @Composable (message: String) -> Unit = {},
    navController: NavHostController
) {
    val uiState: UiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
        when (val state = uiState.profileUiState) {
            is ProfileUiState.Loading -> {
                LoadingScreen()
            }

            is ProfileUiState.Success -> {
                ProfileCard(
                    modifier = Modifier.padding(paddingValues),
                    profile = state.profile,
                    onLogout = {
                        viewModel.logout()
                        navController.navigate(NavigationRoutes.LoginScreen.route)
                    }
                )
            }

            is ProfileUiState.Error -> {
                LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar(
                        message = state.message, actionLabel = "Retry"
                    )
                }
                onError(state.message)
            }
        }
    }
}