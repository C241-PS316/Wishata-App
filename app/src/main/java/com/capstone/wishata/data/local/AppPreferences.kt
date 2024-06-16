package com.capstone.wishata.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.concurrent.Volatile

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "appPreferences")

class AppPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val USERNAME = stringPreferencesKey("username")

    fun getUsername() : Flow<String> {
        return dataStore.data.map { pref ->
            pref[USERNAME].toString()
        }
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit { pref ->
            pref[USERNAME] = username
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = AppPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}