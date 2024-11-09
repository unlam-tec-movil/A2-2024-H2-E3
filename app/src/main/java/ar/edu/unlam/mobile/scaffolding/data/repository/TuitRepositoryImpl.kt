package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.network.ApiService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
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
        TODO("Not yet implemented")
    }
}
