package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.data.network.ApiService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TuitRepositoryImpl(
    private val apiService: ApiService,
) : TuitRepository {
    override suspend fun getTuits(): Flow<List<Tuit>> =
        flow {
            try {
                val response = apiService.getFeed()
                if (response.isSuccessful) {
                    emit(response.body() ?: emptyList())
                } else {
                    throw Exception("Error ${response.code()}")
                }
            } catch (e: Exception) {
                throw e
            }
        }
}
