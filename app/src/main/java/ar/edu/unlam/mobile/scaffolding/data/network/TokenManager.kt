package ar.edu.unlam.mobile.scaffolding.data.network

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class TokenManager
    @Inject
    constructor(
        context: Context,
    ) {
        private val preferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        companion object {
            private const val PREFS_NAME = "token_prefs"
            private const val KEY_USER_TOKEN = "Authorization"
            private const val KEY_APP_TOKEN = "Application-Token"
        }

        fun saveUserToken(token: String) {
            preferences.edit().putString(KEY_USER_TOKEN, token).apply()
        }

        fun getUserToken(): String? = preferences.getString(KEY_USER_TOKEN, null)

        fun saveAppToken(token: String) {
            preferences.edit().putString(KEY_APP_TOKEN, token).apply()
        }

        fun getAppToken(): String? = preferences.getString(KEY_APP_TOKEN, null)
    }
