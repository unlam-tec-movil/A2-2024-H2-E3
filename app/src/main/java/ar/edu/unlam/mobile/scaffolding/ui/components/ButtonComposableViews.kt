package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun NormalButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(), onClick = onClick
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ClickableText(
    modifier: Modifier, text: String, onClick: () -> Unit
) {
    Text(
        text = text,
        color = Color.Blue,
        style = TextStyle(
            textDecoration = TextDecoration.Underline,
            fontSize = 16.sp,
        ),
        modifier = modifier.clickable { onClick() },
    )
}