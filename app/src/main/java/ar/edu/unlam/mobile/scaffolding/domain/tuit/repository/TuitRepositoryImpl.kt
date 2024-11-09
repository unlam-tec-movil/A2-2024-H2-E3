package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit

abstract class TuitRepositoryImpl : TuitRepository {
    private val tuits = mutableListOf<Tuit>() // Ejemplo simple de almacenamiento en memoria

    override suspend fun createTuit(tuit: Tuit) {
        // Agrega el tuit a la lista (simulando una operación de creación)
        tuits.add(tuit)
    }
}
