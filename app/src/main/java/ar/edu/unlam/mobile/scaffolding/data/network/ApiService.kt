package ar.edu.unlam.mobile.scaffolding.data.network

import ar.edu.unlam.mobile.scaffolding.data.network.request.LoginRequest
import ar.edu.unlam.mobile.scaffolding.data.network.response.LoginResponse
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/v1/me/feed")
    suspend fun getFeed(): Response<List<Tuit>>

    @GET("/api/v1/me/profile")
    suspend fun getProfile(): User

    @POST("/api/v1/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>

    @POST("/api/v1/users")
    suspend fun createUser(
        @Body user: User,
    ): Response<User>

    @POST("/api/v1/me/tuits")
    suspend fun createTuit(
        @Body tuit: Tuit,
    ): Response<Tuit>

    @POST("/api/v1/me/tuits/{tuit_id}/likes")
    suspend fun addLike(
        @Path("tuit_id") tuitId: Int,
    ): Response<Unit>

    @DELETE("/api/v1/me/tuits/{tuit_id}/likes")
    suspend fun deleteLike(
        @Path("tuit_id") tuitId: Int,
    ): Response<Unit>
}
