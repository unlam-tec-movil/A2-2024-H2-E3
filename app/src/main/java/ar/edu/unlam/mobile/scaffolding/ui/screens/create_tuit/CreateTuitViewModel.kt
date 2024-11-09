package ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState.TuitUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTuitViewModel
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) : ViewModel() {
        // Creamos un StateFlow para manejar el estado de la UI
        private val _uiState = MutableStateFlow<TuitUIState>(TuitUIState.Loading)
        val uiState: StateFlow<TuitUIState> get() = _uiState

        // Función para crear el tuit
        fun crearTuit(contenido: String) {
            // Cambiar el estado a Loading mientras se crea el tuit
            _uiState.value = TuitUIState.Loading

            viewModelScope.launch {
                try {
                    // Crea un nuevo tuit con los datos proporcionados
                    val newTuit =
                        Tuit(
                            id = 0, // O el ID adecuado si lo generas dinámicamente
                            authorName = "Nombre del Autor", // Personaliza según sea necesario
                            content = contenido,
                            avatar = "URL del Avatar",
                            likes = 0,
                            liked = false,
                            replies = 0,
                            date = "",
                            reply = { /* Acción vacía o predeterminada */ },
                        )

                    // Llama al repositorio para guardar el nuevo tuit
                    tuitRepository.createTuit(newTuit)

                    // Si todo fue bien, emitimos un estado de éxito
                    _uiState.value = TuitUIState.Success("Tuit publicado exitosamente")
                } catch (e: Exception) {
                    // Si hay un error, emitimos un estado de error
                    _uiState.value = TuitUIState.Error("Error al publicar el tuit: ${e.message}")
                }
            }
        }
    }
