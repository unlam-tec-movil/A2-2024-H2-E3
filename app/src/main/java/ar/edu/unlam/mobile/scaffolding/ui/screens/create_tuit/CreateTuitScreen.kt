package ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.dao.TuitDao
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitForm
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoadingScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState.ErrorScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState.TuitUIState

@Composable
fun CreateTuitScreen(
    viewModel: CreateTuitViewModel = hiltViewModel(),
    onTuitCreated: () -> Unit, // Función para hacer algo después de publicar el tuit
) {
    // Aquí se observa el estado del ViewModel
    val uiState = viewModel.uiState.collectAsState().value
    //val draftContent = viewModel.selectedDraft.collectAsState().value
    var tuitContent by remember { mutableStateOf("") }


    DisposableEffect(Unit) {
        onDispose {
            if (tuitContent.isNotBlank()) {
                viewModel.saveDraft(tuitContent)
            }
        }
    }

    // Crear formulario de tuit
    TuitForm (
        initialContent = tuitContent,
        onContentChange = { newContent -> tuitContent = newContent },
        onSaveDraft = { content -> viewModel.saveDraft(content) },
        onSubmit = { content ->
            viewModel.crearTuit(content)
        }
    )


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
