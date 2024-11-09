package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.network.ApiService
import ar.edu.unlam.mobile.scaffolding.data.local.TokenManager
import ar.edu.unlam.mobile.scaffolding.data.network.request.LoginRequest
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository

class UserRepositoryImpl(
    private val apiService: ApiService, private val tokenManager: TokenManager
) : UserRepository {
    override suspend fun isUserLogged(): Boolean {
        return tokenManager.userToken.isNullOrEmpty()
    }
    override suspend fun login(email: String, password: String) {
        try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                response.body()?.let {
                    tokenManager.userToken = it.token
                }
            } else {
                throw Exception("Error ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun register(user: User) {
        try {
            val response = apiService.createUser(user)
            if (response.isSuccessful) {
                response.body()?.let {
                    tokenManager.userToken = it.token
                }
            } else {
                throw Exception("Error ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}