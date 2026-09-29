package com.ignite.recipes.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {

    companion object {
        const val DID_FETCH_DATA = "DID_FETCH_DATA"
    }

    private var sharedPref: SharedPreferences? = null

    var didFetchData:Boolean
        get() = getDidFetchDataValue()
        set(value) = setDidFetchDataValue(value!!)

    init {
        sharedPref = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
    }

    fun setDidFetchDataValue (done:Boolean) {
        val editor = sharedPref?.edit()
        if (editor != null) {
            editor.putBoolean(DID_FETCH_DATA, done)
            editor.apply()
        }
    }

    fun getDidFetchDataValue ():Boolean {
        return sharedPref?.getBoolean(DID_FETCH_DATA, false) ?: false
    }
}