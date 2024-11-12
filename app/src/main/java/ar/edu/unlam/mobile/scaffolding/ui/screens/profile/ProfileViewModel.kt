package ar.edu.unlam.mobile.scaffolding.ui.screens.profile

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.profile.models.Profile
import ar.edu.unlam.mobile.scaffolding.domain.profile.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface ProfileUiState {
    data class Success(
        val profile: Profile,
    ) : ProfileUiState

    data object Loading : ProfileUiState

    data class Error(
        val message: String,
    ) : ProfileUiState
}

data class UiState(
    val profileUiState: ProfileUiState,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(ProfileUiState.Loading))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getProfile().catch {
                    _uiState.value =
                        UiState(ProfileUiState.Error("Error al cargar el perfil de usuario"))
                }.collect { profile ->
                    _uiState.value = UiState(ProfileUiState.Success(
                        Profile(
                            profile.name,
                            profile.email,
                            profile.avatar_url)
                    ))
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            profileRepository.logout()
        }
    }
}