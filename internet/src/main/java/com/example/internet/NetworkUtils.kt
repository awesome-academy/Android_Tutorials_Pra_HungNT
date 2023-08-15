package com.example.internet

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {

    private const val LOG_TAG = "NetworkUtils"
    private const val BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"
    private const val QUERY_PARAM = "q"
    private const val MAX_RESULTS = "maxResults"
    private const val PRINT_TYPE = "printType"

    fun getBookInfo(queryString: String): String? {

        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        var bookJSONString: String? = null

        try {
            val builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build()

            val requestURL = URL(builtURI.toString())
            urlConnection = requestURL.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            val inputStream: InputStream = urlConnection.inputStream
            reader = BufferedReader(InputStreamReader(inputStream))
            val builder = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                builder.append(line)
                builder.append("\n")
            }

            if (builder.isEmpty()) {
                return null
            }

            bookJSONString = builder.toString()

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            urlConnection?.disconnect()
            try {
                reader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (bookJSONString != null) {
            Log.d(LOG_TAG, bookJSONString)
        }

        return bookJSONString
    }
}

