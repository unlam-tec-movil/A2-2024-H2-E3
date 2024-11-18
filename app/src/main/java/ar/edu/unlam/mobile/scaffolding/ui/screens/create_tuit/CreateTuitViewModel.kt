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
class CreateTuitViewModel @Inject constructor(
    private val tuitRepository: TuitRepository
) : ViewModel() {

    // Creamos un StateFlow para manejar el estado de la UI
    private val _uiState = MutableStateFlow<TuitUIState>(TuitUIState.Loading)
    val uiState: StateFlow<TuitUIState> get() = _uiState

    // Función para crear el tuit
    fun crearTuit(contenido: String, authorName: String, avatarUrl: String) {
        // Cambiar el estado a Loading mientras se crea el tuit
        _uiState.value = TuitUIState.Loading

        viewModelScope.launch {
            try {
                // Crear un nuevo objeto Tuit con los datos proporcionados
                val newTuit = Tuit(
                    id = 0, // Se dejaría como 0 ya que el servidor debe generar el ID
                    authorName = authorName, // El nombre del autor se pasa como argumento
                    content = contenido,
                    avatar = avatarUrl, // URL del avatar también se pasa como argumento
                    likes = 0, // Se inicia con 0 likes
                    liked = false, // El tuit no está marcado como "liked" por defecto
                    replies = 0, // Inicia con 0 replies
                    date = getCurrentDate(), // Llama a una función que genera la fecha actual
                    reply = null // Inicialmente no hay respuesta, si fuera necesario se puede ajustar
                )

                // Llama al repositorio para guardar el nuevo tuit
                tuitRepository.createTuit(newTuit)

                // Emitimos un estado de éxito con un mensaje
                _uiState.value = TuitUIState.Success("Tuit publicado exitosamente")
            } catch (e: Exception) {
                // Emitimos un estado de error con el mensaje correspondiente
                _uiState.value = TuitUIState.Error("Error al publicar el tuit: ${e.message}")
            }
        }
    }

    // Función para obtener la fecha actual (puedes personalizarla según tus necesidades)
    private fun getCurrentDate(): String {
        val currentDate = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
        return currentDate.format(java.util.Date())
    }
}
