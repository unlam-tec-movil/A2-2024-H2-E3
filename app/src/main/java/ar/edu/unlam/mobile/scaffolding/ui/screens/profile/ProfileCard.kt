package ar.edu.unlam.mobile.scaffolding.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.profile.models.Profile
import ar.edu.unlam.mobile.scaffolding.ui.components.NormalButton
import coil.compose.AsyncImage

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    profile: Profile,
    onLogout: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Imagen de avatar
        if (profile.avatar.isEmpty()) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
            )
        } else {
            AsyncImage(
                model = profile.avatar,
                contentDescription = "avatar",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Nombre de usuario
        Text(
            text = profile.username,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Correo electrónico
        Text(
            text = profile.email, style = MaterialTheme.typography.bodyLarge, color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        NormalButton(modifier = Modifier, text = "Cerrar sesion", onClick = { onLogout() })
    }

    // Botón grande al final de la pantalla
}