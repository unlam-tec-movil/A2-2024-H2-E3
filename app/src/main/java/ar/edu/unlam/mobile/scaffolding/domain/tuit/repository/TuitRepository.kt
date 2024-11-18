package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.data.local.entities.TuitEntity
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    suspend fun getTuits(): Flow<List<Tuit>>

    suspend fun createTuit(newTuit: Tuit)

    suspend fun addLike(id: Int)

    suspend fun deleteLike(tuitId: Int)

    fun getAllDrafts(): Flow<List<TuitEntity>>

    suspend fun getDraft(tuitId: Int): TuitEntity?

    suspend fun saveDraft(tuitEntity: TuitEntity)

    suspend fun deleteDraft(tuitEntity: TuitEntity)
}
