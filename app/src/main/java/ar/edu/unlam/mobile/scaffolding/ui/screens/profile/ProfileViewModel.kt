package ar.edu.unlam.mobile.scaffolding.ui.screens.profile

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

}