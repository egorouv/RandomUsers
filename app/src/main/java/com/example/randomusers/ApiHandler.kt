package com.example.randomusers

import java.io.BufferedReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ApiHandler {

    private val url = "https://randomuser.me/api/"

    fun getResponse(): String {
        val connection = URL(url).openConnection() as HttpsURLConnection
        connection.requestMethod = "GET"
        connection.connect()
        val response = connection.inputStream.bufferedReader().use(BufferedReader::readText)
        connection.disconnect()
        return response
    }

}