package jp.cordea.ribsdemo

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

class KeyManager @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val KEY = "key"
    }

    fun get(): String? =
        PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY, null)

    fun set(key: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(KEY, key)
            .apply()
    }
}
