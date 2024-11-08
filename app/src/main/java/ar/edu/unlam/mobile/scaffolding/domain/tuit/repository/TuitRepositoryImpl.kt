package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TuitRepositoryImpl : TuitRepository {
    private val tuits = mutableListOf<Tuit>() // Ejemplo simple de almacenamiento en memoria

    override fun getTuits(): Flow<List<Tuit>> {
        // Retorna los tuits como un flujo
        return flow { emit(tuits) }
    }

    override suspend fun createTuit(tuit: Tuit) {
        // Agrega el tuit a la lista (simulando una operación de creación)
        tuits.add(tuit)
    }
}