package ar.edu.unlam.mobile.scaffolding.domain.user.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface UserRepository {
    suspend fun isUserLogged(): Boolean
    suspend fun register(user: User)
    suspend fun login(email: String, password: String)
}