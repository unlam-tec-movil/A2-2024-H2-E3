package ar.edu.unlam.mobile.scaffolding.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val userToken = tokenManager.getUserToken()
        val appToken = tokenManager.getAppToken()

        if (!userToken.isNullOrEmpty() && !appToken.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", userToken)
            requestBuilder.addHeader("Application-Token", appToken)
        }
        Log.d("error", "user: $userToken    app: $appToken")
        return chain.proceed(requestBuilder.build())
    }
}
