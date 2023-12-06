package com.application.weather.manager

import android.content.Context

class SessionManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)

    fun saveSession(name: String, nickname: String) {
        sharedPreferences.edit()
            .putBoolean(LOGIN_STATUS, true)
            .putString(EXTRA_NAME, name)
            .putString(EXTRA_NICKNAME, nickname)
            .apply()
    }

    companion object {
        const val LOGIN_PREF = "LoginPreferences"
        const val LOGIN_STATUS = "isLoggedIn"
        const val EXTRA_NAME = "name"
        const val EXTRA_NICKNAME = "nickname"
    }
}