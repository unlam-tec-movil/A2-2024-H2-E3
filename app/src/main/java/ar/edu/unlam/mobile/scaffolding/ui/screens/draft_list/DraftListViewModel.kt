package ar.edu.unlam.mobile.scaffolding.ui.screens.draft_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.dao.TuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.entities.TuitEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DraftViewModel @Inject constructor(
    private val draftDao: TuitDao
) : ViewModel() {
    val drafts = draftDao.getAllDrafts()

    fun deleteDraft(draft: TuitEntity) {
        viewModelScope.launch {
            draftDao.deleteDraft(draft)
        }
    }
}