package ar.edu.unlam.mobile.scaffolding.data.local

import android.content.Context
import ar.edu.unlam.mobile.scaffolding.BuildConfig
import javax.inject.Inject

class TokenManager
    @Inject
    constructor(
        context: Context,
    ) {
        private val preferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

        var userToken: String?
            get() =
                preferences.getString(
                    "Authorization",
                    @Suppress("ktlint:standard:max-line-length")
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3Q1QHRlc3QuY29tIiwiZXhwIjoxNzMxNDUxNDIwLCJpc3MiOiJ1bmxhbS10dWl0ZXIiLCJuYW1lIjoidGVzdDVAdGVzdC5jb20iLCJzdWIiOjQ0fQ.AMG4NRSfDA8V9ER36HZpwBX2I2SnNHPVC6-ArsPL-hU",
                )
            set(value) = preferences.edit().putString("Authorization", value).apply()

        val appToken: String = BuildConfig.API_KEY
    }
