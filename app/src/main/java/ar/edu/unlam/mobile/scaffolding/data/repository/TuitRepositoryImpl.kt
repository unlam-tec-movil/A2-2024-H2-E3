package ar.edu.unlam.mobile.scaffolding.data.repository

import android.util.Log
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
) : TuitRepository {

    override suspend fun getTuits(): Flow<List<Tuit>> =
        flow {
            val response = apiService.getFeed()
            emit(response)
        }.flowOn(Dispatchers.IO)

    override suspend fun createTuit(tuit: Tuit) {
        try {
            val response = apiService.createTuit(tuit)
            if (!response.isSuccessful) {
                Log.e("TuitRepository", "Error al crear tuit: ${response.message()}")
                throw Exception("Error al crear tuit: ${response.message()}")
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
            Log.e("TuitRepository", "Error addLike: ${e.message}")
            throw e
        }
    }

    override suspend fun deleteLike(tuitId: Int) {
        try {
            apiService.deleteLike(tuitId)
        } catch (e: HttpException) {
            Log.e("TuitRepository", "Error deleteLike: ${e.message}")
            throw e
        }
    }
}