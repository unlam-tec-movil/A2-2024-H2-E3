package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var content by remember { mutableStateOf(initialContent) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
                onContentChange(it)},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { onSaveDraft(content) }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onSubmit(content) }) {
                Text("Send")
            }
        }

    }
}