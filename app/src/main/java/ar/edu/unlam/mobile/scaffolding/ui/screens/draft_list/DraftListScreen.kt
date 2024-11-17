package ar.edu.unlam.mobile.scaffolding.ui.screens.draft_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DraftListScreen(
    viewModel: DraftViewModel = hiltViewModel(),
    onDraftSelected: (Int) -> Unit
) {
    val drafts by viewModel.drafts.collectAsState(initial = emptyList())
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(drafts) { draft ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDraftSelected(draft.id) }
                    .padding(8.dp)
            ) {
                Text(draft.content, modifier = Modifier.weight(1f))
                IconButton(onClick = { viewModel.deleteDraft(draft) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar borrador")
                }
            }
        }
    }
}