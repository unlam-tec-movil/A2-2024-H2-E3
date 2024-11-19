package ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.entities.TuitEntity
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState.TuitUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTuitViewModel
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) : ViewModel() {
        private val _uiState = MutableSharedFlow<TuitUIState>()
        val uiState: SharedFlow<TuitUIState> = _uiState.asSharedFlow()

        fun saveDraft(content: String) {
            viewModelScope.launch(Dispatchers.IO) {
                tuitRepository.saveDraft(TuitEntity(content = content))
            }
        }

        // Función para crear el tuit
        fun crearTuit(contenido: String) {
            // Cambiar el estado a Loading mientras se crea el tuit

            viewModelScope.launch {
                _uiState.emit(TuitUIState.Loading)
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
                            reply = "",
                        )
                    // Llama al repositorio para guardar el nuevo tuit
                    tuitRepository.createTuit(newTuit)

                    // Si fue bien, emitimos un estado de éxito
                    _uiState.emit(TuitUIState.Success("Tuit publicado exitosamente"))
                } catch (e: Exception) {
                    // Si hay un error, emitimos un estado de error
                    _uiState.emit(TuitUIState.Error("Error al publicar el tuit: ${e.message}"))
                }
            }
        }
    }
