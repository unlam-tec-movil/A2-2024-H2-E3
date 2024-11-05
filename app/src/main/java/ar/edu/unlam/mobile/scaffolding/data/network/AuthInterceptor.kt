package ar.edu.unlam.mobile.scaffolding.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain
                .request()
                .newBuilder()
                .apply {
                    tokenManager.userToken?.let { addHeader("Authorization", "Bearer $it") }
                    tokenManager.appToken?.let { addHeader("Application-Token", it) }
                }.build()
        Log.d("AuthInterceptor", "userToken: ${tokenManager.userToken}, appToken: ${tokenManager.appToken}")
        return chain.proceed(request)
    }
}
