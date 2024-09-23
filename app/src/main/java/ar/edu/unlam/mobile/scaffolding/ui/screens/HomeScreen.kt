package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.components.Greeting
import ar.edu.unlam.mobile.scaffolding.ui.components.Home

@Composable
fun HomeScreen(
    tuits: List<Tuit>,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    // La información que obtenemos desde el view model la consumimos a través de un estado de
    // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
    // un estado de éxito y un mensaje de error.
    val uiState: HomeUIState by viewModel.uiState.collectAsState()

    when (val helloState = uiState.helloMessageState) {
        is HelloMessageUIState.Loading -> {
            // Loading
        }

        is HelloMessageUIState.Success -> {
            //Greeting(helloState.message, modifier)
        }

        is HelloMessageUIState.Error -> {
            // Error
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Home(tuits, modifier)
}
