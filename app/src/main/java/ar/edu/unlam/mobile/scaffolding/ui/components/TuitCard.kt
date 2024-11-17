package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.screens.home.HomeViewModel
import coil.compose.AsyncImage

@Composable
fun TuitCard(
    tuit: Tuit,
    viewModel: HomeViewModel,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TuitImage(tuit)

            Spacer(modifier = Modifier.width(16.dp))

            TuitContent(tuit)
        }
        Row {
            Spacer(modifier = Modifier.width(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(2.dp)
            ) {
                TuitLike(tuit, viewModel)
                TuitLikesCounter(tuit)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(2.dp)
            ) {
                TuitReply(tuit)
                TuitRepliesCounter(tuit)
            }
        }
    }
}

@Composable
fun TuitReply(tuit: Tuit) {
    IconButton(onClick = { tuit.replies++ }) {
        Icon(
            imageVector = Icons.Filled.Email, // cambiar
            contentDescription = "add like",
        )
    }
}

@Composable
fun TuitImage(tuit: Tuit) {
    if (tuit.avatar.isNullOrEmpty()) {
        Box(
            modifier =
                Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
        )
    } else {
        AsyncImage(
            model = tuit.avatar,
            contentDescription = "avatar",
            modifier =
                Modifier
                    .size(50.dp)
                    .clip(CircleShape),
        )
    }
}

@Composable
fun TuitContent(tuit: Tuit) {
    Row {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(4.dp))
                    .padding(16.dp),
        ) {
            Text(
                text = tuit.authorName,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start),
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = tuit.content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Start),
            )
        }
    }
}

@Composable
fun TuitLike(
    tuit: Tuit,
    viewModel: HomeViewModel,
) {
    IconButton(onClick = { viewModel.likeTuit(tuit)}) {
        Icon(
            imageVector = if (tuit.liked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
            contentDescription = if (tuit.liked) "delete like" else "add like",
            tint = if (tuit.liked) Color.Blue else Color.Gray
        )
    }
}

@Composable
fun TuitLikesCounter(tuit: Tuit) {
    Text(
        text = tuit.likes.toString(),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(2.dp),
    )
}

@Composable
fun TuitRepliesCounter(tuit: Tuit) {
    Text(
        text = tuit.replies.toString(),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(4.dp),
    )
}
