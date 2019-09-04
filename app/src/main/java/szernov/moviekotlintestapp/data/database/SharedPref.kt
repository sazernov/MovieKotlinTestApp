package szernov.moviekotlintestapp.data.database

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPref(applicationContext: Context) {

    private val mSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    val lastUpdatedTime: Long
        get() = mSharedPreferences.getLong(PREF_LAST_UPDATE_TIME, -1)


    fun saveLastUpdatedTime(time: Long) {
        mSharedPreferences.edit()
            .putLong(PREF_LAST_UPDATE_TIME, time)
            .apply()
    }

    companion object {
        const val PREF_LAST_UPDATE_TIME = "PREF_LAST_UPDATE_TIME"
    }
}