package com.example.randomusers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SaveState(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUserList(users: List<User>) {
        val json = gson.toJson(users)
        sharedPreferences.edit().putString("user_list", json).apply()
    }

    fun getUserList(): List<User> {
        val json = sharedPreferences.getString("user_list", null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<List<User>>() {}.type)
        } else {
            emptyList()
        }
    }

}