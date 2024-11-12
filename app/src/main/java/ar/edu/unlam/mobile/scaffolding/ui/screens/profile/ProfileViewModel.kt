package ar.edu.unlam.mobile.scaffolding.ui.screens.profile

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.profile.models.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
//    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(ProfileUiState.Loading))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = UiState(ProfileUiState.Success(
                Profile(
                    username = "Robert Downey Jr",
                    email = "Robert@gmail.com",
                    avatar = "https://pluspng.com/img-png/iron-man-png-hd-iron-man-clipart-png-image-512.png")))
        }
    }
}