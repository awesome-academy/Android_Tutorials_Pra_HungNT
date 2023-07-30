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

    /** url  API BOOK **/
    private const val BOOK_BASE_URL =
        "https://www.googleapis.com/books/v1/volumes?"

    /** Param**/
    private const val QUERY_PARAM = "q"
    /** Param giới hạn kết quả trả về**/
    private const val MAX_RESULTS = "maxResults"
    /** lọc kết quả sách theo loại sách**/
    private const val PRINT_TYPE = "printType"

    fun getBookInfo(queryString: String): String? {

        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        var bookJSONString: String? = null

        try {
    /**Xây dựng  uri truy vấn , giói hạn 10 book**/
            val builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build()

            /** convert uri -> url**/
            val requestURL = URL(builtURI.toString())

            /** Open the network connection**/
            urlConnection = requestURL.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            /** Get the InputStream**/
            val inputStream: InputStream = urlConnection.inputStream

            /** Create a buffered reader from that input stream.*/
            reader = BufferedReader(InputStreamReader(inputStream))

            /** Use a StringBuilder to hold the incoming response.*/
            val builder = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
               /**Thêm dòng hiện tại vào chuỗi*/
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
            /** Close the connection and the buffered reader*/
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
