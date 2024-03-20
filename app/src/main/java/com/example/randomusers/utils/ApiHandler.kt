package com.example.randomusers.utils

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class ApiHandler {

    interface ApiCallback {
        fun onResponse(result: String)
        fun onFailure(error: String)
    }

    private val url = "https://randomuser.me/api/?results=10"

    fun getResponse(callback: ApiCallback) {
        val request: Request = Request.Builder()
            .url(url)
            .build()

        OkHttpClient().newCall(request)
            .enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    callback.onFailure(e.message ?: "Unknown error")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val result = response.body?.string()
                        if (result != null) {
                            callback.onResponse(result)
                        } else {
                            callback.onFailure("Empty response")
                        }
                    } else {
                        callback.onFailure("Unsuccessful response: ${response.code}")
                    }
                }
            })
    }
}
