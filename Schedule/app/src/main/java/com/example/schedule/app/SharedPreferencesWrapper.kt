package com.example.schedule.app

import android.app.Application
import android.content.SharedPreferences


private val SPNAME = "MyPref"
private val LOGIN_KEY = "LOGIN_KEY"

class SharedPreferencesWrapper(private val application: Application){

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = application.getSharedPreferences(SPNAME, 0)
    }

    fun setToken(token: String){
        val editor = sharedPreferences.edit()
        editor.putString(LOGIN_KEY, token)
        editor.apply()
    }

    fun getToken(): String = sharedPreferences.getString(LOGIN_KEY, null) ?: ""

}