package ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitForm
import ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState.TuitUIState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CreateTuitScreen(
    initialContent: String = "",
    viewModel: CreateTuitViewModel = hiltViewModel(),
    onTuitCreated: () -> Unit, // Función para hacer algo después de publicar el tuit
    onDraftSaved: () -> Unit,
    snackBarHostState: SnackbarHostState,
) {
    val tuitContent = remember { mutableStateOf(initialContent) }
    // Aquí se observa el estado del ViewModel
    LaunchedEffect(Unit) {
        viewModel.uiState.collectLatest { uiState ->
            when (uiState) {
                is TuitUIState.Success -> {
                    // Acción cuando el tuit fue publicado correctamente
                    // onTuitCreated() // Regresa a la pantalla de inicio tras publicar
                    snackBarHostState.showSnackbar(uiState.message, duration = SnackbarDuration.Short)
                    onTuitCreated()
                }

                is TuitUIState.Error -> {
                    // Mostrar mensaje de error si la creación del tuit falla
                    // Aquí podrías usar un Snackbar, Toast o mostrar un mensaje de error
                    snackBarHostState.showSnackbar(uiState.error, duration = SnackbarDuration.Short)
                }

                else -> Unit
            }
        }
    }

    // Crear formulario de tuit
    TuitForm(
        initialContent = tuitContent.value,
        onContentChange = { tuitContent.value = it },
        onSubmit = { content ->
            if (content.isNotBlank()) {
                viewModel.crearTuit(content)
            }
        },
        onSaveDraft = { content ->
            if (content.isNotBlank()) {
                viewModel.saveDraft(content)
                onDraftSaved()
            }
        },
    )
}
