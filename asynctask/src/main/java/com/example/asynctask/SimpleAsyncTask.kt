package com.example.asynctask

import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ref.WeakReference
import java.util.Random

class SimpleAsyncTask(textStatus: TextView, progressBar: ProgressBar) : AsyncTask<Void, Int, String>() {
    private val mTextStatus: WeakReference<TextView> = WeakReference(textStatus)
    private val mProgressBar: WeakReference<ProgressBar> = WeakReference(progressBar)

    override fun onPreExecute() {
        super.onPreExecute()
        mProgressBar.get()?.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg voids: Void): String? {
        val randomGenerator = Random()
        val randomNumber = randomGenerator.nextInt(11)
        val sleepDuration = randomNumber * 200
        val sleepIncrement = sleepDuration / 100

        try {
            for (i in 0..100) {
                Thread.sleep(sleepIncrement.toLong())
                publishProgress(i)
                if (isCancelled) {
                    return null
                }
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return "Awake at last after sleeping for $sleepDuration milliseconds!"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        mProgressBar.get()?.progress = values[0] ?: 0
    }

    override fun onPostExecute(result: String) {
        mProgressBar.get()?.visibility = View.INVISIBLE
        if (result != null) {
            mTextStatus.get()?.text = result
        }
    }
}


