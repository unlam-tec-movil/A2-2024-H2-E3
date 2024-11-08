package ar.edu.unlam.mobile.scaffolding.ui.screens.create_tuit.UiState

sealed class TuitUIState {
    object Loading : TuitUIState() // Estado de carga
    data class Success(val message: String) : TuitUIState() // Estado de éxito
    data class Error(val error: String) : TuitUIState() // Estado de error
}