package ar.edu.unlam.mobile.scaffolding.ui.screens.TuitScreens.UiState

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorScreen(errorMessage: String) {
    // Puedes usar un Snackbar o un Simple Text para mostrar el error
    Text(
        text = errorMessage,
        color = Color.Red,
        style = MaterialTheme.typography.bodyLarge
    )
}