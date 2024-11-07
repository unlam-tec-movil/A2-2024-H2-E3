package ar.edu.unlam.mobile.scaffolding.ui.screens.TuitScreens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitForm
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoadingScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.TuitScreens.UiState.TuitUIState
import ar.edu.unlam.mobile.scaffolding.ui.screens.TuitScreens.UiState.ErrorScreen

@Composable
fun CrearTuitScreen(
    viewModel: CrearTuitViewModel = hiltViewModel(),
    onTuitPublicado: () -> Unit // Función para hacer algo después de publicar el tuit
) {
    // Aquí se observa el estado del ViewModel
    val uiState = viewModel.uiState.collectAsState().value

    // Crear formulario de tuit
    TuitForm { contenidoTuit ->
        // Llamar a la función que crea el tuit
        viewModel.crearTuit(contenidoTuit)
    }

    // Manejo del estado
    when (uiState) {
        is TuitUIState.Loading -> {
            // Mostrar pantalla de carga
            LoadingScreen()
        }
        is TuitUIState.Success -> {
            // Acción cuando el tuit fue publicado correctamente
            onTuitPublicado() // Regresa a la pantalla de inicio tras publicar
        }
        is TuitUIState.Error -> {
            // Mostrar mensaje de error si la creación del tuit falla
            // Aquí podrías usar un Snackbar, Toast o mostrar un mensaje de error
            ErrorScreen(errorMessage = uiState.error)
        }
    }
}
