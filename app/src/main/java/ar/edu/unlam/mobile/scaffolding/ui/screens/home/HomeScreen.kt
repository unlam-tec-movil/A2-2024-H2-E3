package ar.edu.unlam.mobile.scaffolding.ui.screens.home
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.Feed
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoadingScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.NavigationRoutes

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onError: @Composable (message: String) -> Unit = {},
    navController: NavHostController
) {
    val uiState: TuitUIState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    TODO agregar funcionalidad de navigacion en MainActivity
//                    navController.navigate(NavigationRoutes.CreateTuitScreen.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear Tuit"
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        when (val tuitState = uiState.feedUiState) {
            is FeedUIState.Loading -> {
                LoadingScreen()
            }
            is FeedUIState.Success -> {
                Feed(tuits = tuitState.tuits, modifier = modifier.padding(paddingValues))
            }
            is FeedUIState.Error -> {
                LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar(
                        message = tuitState.message,
                        actionLabel = "Retry"
                    )
                }
                onError(tuitState.message)
            }
        }
    }
}

