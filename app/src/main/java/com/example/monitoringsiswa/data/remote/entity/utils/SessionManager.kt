package com.example.monitoringsiswa.data.remote.entity.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.monitoringsiswa.Constanta.ACCESS_ID
import com.example.monitoringsiswa.R

class SessionManager(context: Context) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun saveAccessId(id: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_ID, id)
            .apply()
    }

    fun fetchAccessId(): String? {
        return prefs.getString(ACCESS_ID, null)
    }

    fun deleteAccessToken() {
        val editor = prefs.edit()
        editor.clear()
            .apply()
    }



}