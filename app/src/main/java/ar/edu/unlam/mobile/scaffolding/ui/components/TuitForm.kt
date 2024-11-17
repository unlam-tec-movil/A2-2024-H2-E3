package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun TuitForm(
    initialContent: String,
    onContentChange: (String) -> Unit,
    onSaveDraft: (String) -> Unit,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = initialContent,
            onValueChange = { newText -> onContentChange(newText) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { if (initialContent.isNotBlank()) {
                    onSaveDraft(initialContent)
            } }) {
                Text("Save")
            }
            Button(
                onClick = { if (initialContent.isNotBlank()) onSubmit(initialContent) }
            ) {
                Text("Send")
            }
        }

    }
}