package com.example.internet

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class BookLoader(context: Context, private val mQueryString: String) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }
    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(mQueryString)
    }
}

