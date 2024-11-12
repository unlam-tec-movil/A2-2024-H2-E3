package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.TokenManager
import ar.edu.unlam.mobile.scaffolding.data.network.ApiService
import ar.edu.unlam.mobile.scaffolding.domain.profile.models.Profile
import ar.edu.unlam.mobile.scaffolding.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProfileRepositoryImpl(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
): ProfileRepository {
    override suspend fun getProfile(): Flow<Profile> =
//        Flow{emit(Profile("", "", ""))}
        flow {
            val response = apiService.getProfile()
            emit(response)
        }.flowOn(Dispatchers.IO)

    override suspend fun logout() {
        tokenManager.userToken = null
    }
//    override suspend fun updateProfile(profile: Profile) {
//    }
}