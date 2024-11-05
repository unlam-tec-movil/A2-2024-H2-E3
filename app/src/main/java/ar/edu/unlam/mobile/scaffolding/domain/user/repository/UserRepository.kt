package ar.edu.unlam.mobile.scaffolding.domain.user.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface UserRepository {
    suspend fun isUserLogged(): Boolean
    suspend fun logUser(email: String, password: String): Boolean
    suspend fun registerNewUser(user: User): Boolean
}