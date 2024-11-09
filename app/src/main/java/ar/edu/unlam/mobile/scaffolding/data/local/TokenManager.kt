package ar.edu.unlam.mobile.scaffolding.data.local

import android.content.Context
import javax.inject.Inject

class TokenManager
@Inject constructor(
    context: Context,
) {
    private val preferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    var userToken: String?
        get() = preferences.getString("Authorization", "")
        set(value) = preferences.edit().putString("Authorization", value).apply()

    var appToken: String?
        get() = preferences.getString("Application-Token", "")
        set(value) = preferences.edit().putString("Application-Token", value).apply()
}
