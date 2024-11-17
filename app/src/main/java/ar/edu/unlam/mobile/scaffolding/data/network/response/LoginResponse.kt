package ar.edu.unlam.mobile.scaffolding.data.network.response

data class LoginResponse(
    val email: String,
    val password: String,
    val token: String,
)