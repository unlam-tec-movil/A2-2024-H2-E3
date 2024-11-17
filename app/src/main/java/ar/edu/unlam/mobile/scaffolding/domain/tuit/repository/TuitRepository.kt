package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    suspend fun getTuits(): Flow<List<Tuit>>

    suspend fun createTuit(tuit: Tuit)

    suspend fun addLike(id: Int)

    suspend fun deleteLike(tuitId: Int)
}
