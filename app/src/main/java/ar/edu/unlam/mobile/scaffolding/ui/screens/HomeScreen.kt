package ar.edu.unlam.mobile.scaffolding.ui.screens
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
import ar.edu.unlam.mobile.scaffolding.ui.components.BottomBar

//@Composable
//fun HomeScreen(
    //modifier: Modifier = Modifier,
    //viewModel: HomeViewModel = hiltViewModel(),
//    onError: @Composable (message: String) -> Unit = {}, )//{

    // La información que obtenemos desde el view model la consumimos a través de un estado de
    // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
    // un estado de éxito y un mensaje de error.
//    val uiState: TuitUIState by viewModel.uiState.collectAsState()

    //when (val tuitState = uiState.feedUiState) {
        //is FeedUIState.Loading -> {
            // Loading
        //    LoadingScreen()
        //}

        //is FeedUIState.Success -> {
            // Greeting(helloState.message, modifier)
          //  Feed(tuits = tuitState.tuits, modifier)
        //}

        //is FeedUIState.Error -> {
            // Error
        //    onError(tuitState.message)
      //  }
    //}
//}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onError: @Composable (message: String) -> Unit = {},
    navController: NavHostController // Añadimos el controlador de navegación
) {
    val uiState: TuitUIState by viewModel.uiState.collectAsState()

    // Inicializamos el estado de Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Usamos Scaffold para estructurar la pantalla, que incluye el FAB y la BottomBar
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(controller = navController) // Barra de navegación inferior
        },
        floatingActionButton = {
            // FloatingActionButton para navegar a la pantalla de creación de Tuit
            FloatingActionButton(
                onClick = {
                    // Navegar a la pantalla de creación de tuit
                    navController.navigate("crearTuitScreen")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear Tuit"
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) } // Agregamos el SnackbarHost
    ) { paddingValues ->
        // El contenido principal de la pantalla
        when (val tuitState = uiState.feedUiState) {
            is FeedUIState.Loading -> {
                LoadingScreen() // Pantalla de carga
            }

            is FeedUIState.Success -> {
                // Mostrar los tuits obtenidos
                Feed(tuits = tuitState.tuits, modifier = modifier.padding(paddingValues))
            }

            is FeedUIState.Error -> {
                // Mostrar el mensaje de error en el Snackbar
                LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar(
                        message = tuitState.message,
                        actionLabel = "Retry"
                    )
                }
                // Llamamos a la función onError para que puedas mostrar el error en algún lugar si lo necesitas
                onError(tuitState.message)
            }
        }
    }
}
