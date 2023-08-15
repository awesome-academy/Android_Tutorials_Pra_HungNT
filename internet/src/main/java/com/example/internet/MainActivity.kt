package com.example.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.example.internet.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (supportLoaderManager.getLoader<String>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
        binding.buttonSearch.setOnClickListener{ searchBooks(it)}
    }



    fun searchBooks(view: View) {
        val queryString =binding.edittextBookInput.text.toString()

        val inputManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo: NetworkInfo? = connMgr?.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected && queryString.isNotEmpty()) {
            val queryBundle = Bundle()
            queryBundle.putString(KEY_QUERY, queryString)
            supportLoaderManager.restartLoader(0, queryBundle, this)

            binding.textAuthor.text = ""
            binding.textTitle.text = getString(R.string.loading)
        }
        else {
            if (queryString.isEmpty()) {
                binding.textAuthor.text = ""
                binding.textTitle.text = getString(R.string.no_search_term)
            } else {
                binding.textAuthor.text = ""
                binding.textTitle.text = getString(R.string.no_network)
            }
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        val queryString = args?.getString(KEY_QUERY) ?: ""
        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {
            val jsonObject = JSONObject(data)
            val itemsArray = jsonObject.getJSONArray(KEY_ITEM)

            var i = 0
            var title: String? = null
            var authors: String? = null

            while (i < itemsArray.length() && (authors == null && title == null)) {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject(KEY_VOLUME_INFO)

                try {
                    title = volumeInfo.getString(KEY_TITLE)
                    val authorsArray = volumeInfo.getJSONArray(KEY_AUTHORS)
                    if (authorsArray.length() > 0) {
                        authors = authorsArray.getString(0)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                i++
            }

            if (title != null && authors != null) {
                binding.textTitle.text = title
                binding.textAuthor.text = authors
            } else {
                binding.textTitle.text = getString(R.string.no_results)
                binding.textAuthor.text =  ""
            }

        } catch (e: Exception) {
            binding.textTitle.text = getString(R.string.no_results)
            binding.textAuthor.text =  ""
            e.printStackTrace()
        }
    }
    override fun onLoaderReset(loader: Loader<String>) {
    }

    companion object{
        private const val KEY_QUERY ="queryString"
        private const val KEY_ITEM = "items"
        private const val KEY_VOLUME_INFO = "volumeInfo"
        private const val KEY_TITLE = "title"
        private const val KEY_AUTHORS = "authors"
    }
}

