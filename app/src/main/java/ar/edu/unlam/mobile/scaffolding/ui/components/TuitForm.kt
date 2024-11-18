package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TuitForm(
    modifier: Modifier = Modifier,
    onSubmit: (String) -> Unit
) {
    val tuitContent = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = tuitContent.value,
            onValueChange = { tuitContent.value = it },
            label = { Text("Escribe tu tuit") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true // Esto limita la entrada a una sola línea
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Deshabilitar el botón si el tuit está vacío o solo tiene espacios
        Button(
            onClick = { onSubmit(tuitContent.value) },
            modifier = Modifier.align(Alignment.End),
            enabled = tuitContent.value.isNotBlank() // Habilitar solo si no está vacío
        ) {
            Text("Publicar Tuit")
        }
    }
}
