package com.example.template.functions.data_manipulation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import com.example.template.model.Users

val globalToken: MutableLiveData<String> = MutableLiveData()
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

lateinit var deletionrequesteduser: Users
val userrole: MutableLiveData<String> = MutableLiveData()
val globalOldEmail: MutableLiveData<String> = MutableLiveData()
//val jwt: MutableLiveData<String> = MutableLiveData()