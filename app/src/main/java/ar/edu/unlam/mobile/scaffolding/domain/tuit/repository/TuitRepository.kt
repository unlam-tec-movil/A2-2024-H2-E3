package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    fun getTuits(): Flow<List<Tuit>>

    // Convertir createTuit en función suspendida
    suspend fun createTuit(tuit: Tuit)
}