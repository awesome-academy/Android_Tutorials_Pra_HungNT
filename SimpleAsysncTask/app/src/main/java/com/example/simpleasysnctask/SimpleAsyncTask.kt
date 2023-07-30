package com.example.simpleasysnctask

import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ref.WeakReference
import java.util.Random

class SimpleAsyncTask(tv: TextView, progressBar: ProgressBar) : AsyncTask<Void, Int, String>() {
/**Params: Là giá trị ((biến) được truyền vào khi gọi thực thi tiến trình và nó sẽ được truyền vào doInBackground
    Progress: Là giá trị (biến) dùng để update giao diện diện lúc tiến trình thực thi, biến này sẽ được truyền vào hàm onProgressUpdate
    Result: Là biến dùng để lưu trữ kết quả trả về sau khi tiến trình thực hiện xong
    Mặc định sẽ là Void
    **/
    private val mTextView: WeakReference<TextView> = WeakReference(tv)
    private val mProgressBar: WeakReference<ProgressBar> = WeakReference(progressBar)
/** Hàm được gọi đầu tiên khi tiến trình được kích hoạt**/
    override fun onPreExecute() {
        super.onPreExecute()
        mProgressBar.get()?.visibility = View.VISIBLE
    }
/**    Được thực thi trong quá trình tiến trình chạy nền, thông qua hàm này để ta gọi hàm
    onProgressUpdate để cập nhật giao diện (gọi lệnh publishProgress).
    Ta không thể cập nhật giao diện trong hàm doInBackground().
**/
    override fun doInBackground(vararg voids: Void): String? {
        val r = Random()
        val n = r.nextInt(11)
        val s = n * 200
        val sleepIncrement = s / 100

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
        return "Awake at last after sleeping for $s milliseconds!\n" +
                "let's start again"
    }
/**Dùng để cập nhật giao diện lúc runtime**/
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        mProgressBar.get()?.progress = values[0] ?: 0
    }
    /**    Sau khi tiến trình kết thúc thì hàm này sẽ tự động xảy ra.
    Ta có thể lấy được kết quả trả về sau khi thực hiện tiến trình kết thúc ở đây.**/
    override fun onPostExecute(s: String?) {
        mProgressBar.get()?.visibility = View.INVISIBLE
        if (s != null) {
            mTextView.get()?.text = s
        }
    }
    override fun onCancelled() {
        super.onCancelled()
        mTextView.get()?.setText(R.string.start)
        mProgressBar.get()?.visibility = View.INVISIBLE
    }
}
