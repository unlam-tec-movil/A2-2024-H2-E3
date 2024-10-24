package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.Feed

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onError: @Composable (message: String) -> Unit = {},
) {
    // La información que obtenemos desde el view model la consumimos a través de un estado de
    // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
    // un estado de éxito y un mensaje de error.
    val uiState: TuitUIState by viewModel.uiState.collectAsState()

    when (val tuitState = uiState.feedUiState) {
        is FeedUIState.Loading -> {
            // Loading
            LoadingScreen()
        }

        is FeedUIState.Success -> {
            // Greeting(helloState.message, modifier)
            Feed(tuits = tuitState.tuits, modifier)
        }

        is FeedUIState.Error -> {
            // Error
            onError(tuitState.message)
        }
    }
}
