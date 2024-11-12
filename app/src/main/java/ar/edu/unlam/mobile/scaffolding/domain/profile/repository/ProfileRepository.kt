package ar.edu.unlam.mobile.scaffolding.domain.profile.repository

import ar.edu.unlam.mobile.scaffolding.domain.profile.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfile(): Flow<Profile>
    suspend fun logout()
//    suspend fun updateProfile(profile: Profile)
}