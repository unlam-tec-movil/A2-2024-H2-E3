package ar.edu.unlam.mobile.scaffolding.ui.screens.TuitScreens

/*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitForm
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoadingScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.TuitScreens.UiState.TuitUIState
import ar.edu.unlam.mobile.scaffolding.ui.screens.TuitScreens.UiState.ErrorScreen

@Composable
fun CrearTuitScreen(
    viewModel: CrearTuitViewModel = hiltViewModel(),
    onTuitPublicado: () -> Unit // Función para hacer algo después de publicar el tuit
) {
    // Aquí se observa el estado del ViewModel
    val uiState = viewModel.uiState.collectAsState().value

    // Crear formulario de tuit
    TuitForm { contenidoTuit ->
        // Llamar a la función que crea el tuit
        viewModel.crearTuit(contenidoTuit)
    }

    // Manejo del estado
    when (uiState) {
        is TuitUIState.Loading -> {
            // Mostrar pantalla de carga
            LoadingScreen()
        }
        is TuitUIState.Success -> {
            // Acción cuando el tuit fue publicado correctamente
            onTuitPublicado() // Regresa a la pantalla de inicio tras publicar
        }
        is TuitUIState.Error -> {
            // Mostrar mensaje de error si la creación del tuit falla
            // Aquí podrías usar un Snackbar, Toast o mostrar un mensaje de error
            ErrorScreen(errorMessage = uiState.error)
        }
    }
}*/

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import java.util.*
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearTuitScreen(
    navController: NavController,
    onTuitCreated: (Tuit) -> Unit
) {
    var tweetContent by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Crear Tweet") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = tweetContent,
                onValueChange = { tweetContent = it },
                placeholder = { Text("Escribe tu tweet aquí...") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Crear un nuevo Tuit
                    val newTweet = Tuit(
                        id = UUID.randomUUID().hashCode(),  // Generar un ID único basado en UUID
                        authorName = "User",                // Reemplaza con el nombre del usuario real si lo tienes
                        content = tweetContent,
                        avatar = "",                        // Puedes reemplazarlo con una URL de avatar predeterminada o dejarlo vacío
                        likes = 0,
                        liked = false,                      // El tuit inicialmente no está marcado como "me gusta"
                        replies = 0,
                        reply = { id -> /* Implementación de respuesta */ }  // Puedes personalizar esta función si es necesario
                    )

                    // Llamar a la función para pasar el nuevo tuit al ViewModel o lista
                    onTuitCreated(newTweet)

                    // Volver a la pantalla anterior (HomeScreen)
                    navController.popBackStack()
                },
                enabled = tweetContent.isNotBlank()
            ) {
                Text("Tweetear")
            }
        }
    }
}*/

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTuitScreen(
    navController: NavController
) {
    var tweetContent by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Crear Tweet") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = tweetContent,
                onValueChange = { tweetContent = it },
                placeholder = { Text("Escribe tu tweet aquí...") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newTweet = Tuit(
                        id = UUID.randomUUID().hashCode(),
                        authorName = "User",
                        content = tweetContent,
                        avatar = "",
                        likes = 0,
                        liked = false,
                        replies = 0,
                        reply = {}
                    )

                    // Guardar el nuevo tweet y navegar hacia atrás
                    navController.previousBackStackEntry?.savedStateHandle?.set("newTuit", newTweet)
                    navController.popBackStack()
                },
                enabled = tweetContent.isNotBlank()
            ) {
                Text("Tweetear")
            }
        }
    }
}

