package ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitForm
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoadingScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState.TuitUIState
import ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState.ErrorScreen

@Composable
fun CreateTuitScreen(
    viewModel: CreateTuitViewModel = hiltViewModel(),
    onTuitCreated: () -> Unit // Función para hacer algo después de publicar el tuit
) {
    // Aquí se observa el estado del ViewModel
    val uiState = viewModel.uiState.collectAsState().value

    // Aquí puedes obtener el nombre del autor y el avatar, esto dependerá de tu lógica de usuario
    val authorName = "Nombre del Autor" // Esto puede provenir de un modelo de usuario autenticado
    val avatarUrl = "URL del Avatar" // Esto también puede ser dinámico según el usuario

    // Crear formulario de tuit
    TuitForm { tuitContent ->
        // Llamar a la función que crea el tuit, pasando todos los parámetros requeridos
        viewModel.crearTuit(tuitContent, authorName, avatarUrl)
    }

    // Manejo del estado
    when (uiState) {
        is TuitUIState.Loading -> {
            // Mostrar pantalla de carga
            LoadingScreen()
        }
        is TuitUIState.Success -> {
            // Acción cuando el tuit fue publicado correctamente
            onTuitCreated() // Regresa a la pantalla de inicio tras publicar
        }
        is TuitUIState.Error -> {
            // Mostrar mensaje de error si la creación del tuit falla
            // Aquí podrías usar un Snackbar, Toast o mostrar un mensaje de error
            ErrorScreen(errorMessage = uiState.error)
        }
    }
}
