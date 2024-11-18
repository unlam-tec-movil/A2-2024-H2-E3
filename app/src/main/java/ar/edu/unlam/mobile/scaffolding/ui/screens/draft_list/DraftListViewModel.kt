package ar.edu.unlam.mobile.scaffolding.ui.screens.draft_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.entities.TuitEntity
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DraftViewModel
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) : ViewModel() {
        val drafts = tuitRepository.getAllDrafts()

        fun deleteDraft(draft: TuitEntity) {
            viewModelScope.launch {
                tuitRepository.deleteDraft(draft)
            }
        }
    }
