package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit

@Composable
fun Home(
    tuits: List<Tuit>,
    modifier: Modifier,
)  {
    LazyColumn(
        modifier =
        modifier
            .fillMaxSize()
            .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()),
    ) {
        items(tuits) { tuit ->
            TuitCard(tuit = tuit)
        }
    }
}