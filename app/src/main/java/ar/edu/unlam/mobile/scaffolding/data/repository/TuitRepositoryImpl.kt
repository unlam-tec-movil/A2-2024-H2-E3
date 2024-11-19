package ar.edu.unlam.mobile.scaffolding.data.repository

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.dao.TuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.entities.TuitEntity
import ar.edu.unlam.mobile.scaffolding.data.network.ApiService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import coil.network.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TuitRepositoryImpl(
    private val apiService: ApiService,
    private val tuitDao: TuitDao,
) : TuitRepository {
    override suspend fun getTuits(): Flow<List<Tuit>> =
        flow {
            val response = apiService.getFeed()
            emit(response)
        }.flowOn(Dispatchers.IO)

    override suspend fun createTuit(newTuit: Tuit) {
        try {
            val response = apiService.createTuit(newTuit)
            Log.d("TuitRepository", "Response: $response")
            if (!response.isSuccessful) {
                throw Exception(response.code().toString())
            }
        } catch (e: HttpException) {
            Log.e("TuitRepository", "Error en la API al crear tuit: ${e.message}")
            throw e
        } catch (e: Exception) {
            Log.e("TuitRepository", "Error inesperado al crear tuit: ${e.message}")
            throw e
        }
    }

    override suspend fun addLike(tuitId: Int) {
        try {
            apiService.addLike(tuitId)
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun deleteLike(tuitId: Int) {
        try {
            apiService.deleteLike(tuitId)
        } catch (e: HttpException) {
            throw e
        }
    }

    override fun getAllDrafts(): Flow<List<TuitEntity>> = tuitDao.getAllDrafts()

    override suspend fun getDraft(tuitId: Int): TuitEntity? = tuitDao.getDraft(tuitId)

    override suspend fun saveDraft(tuitEntity: TuitEntity) = tuitDao.saveDraft(tuitEntity)

    override suspend fun deleteDraft(tuitEntity: TuitEntity) = tuitDao.deleteDraft(tuitEntity)
}
