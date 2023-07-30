package com.example.simpleasysnctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.wear.ongoing.Status

class MainActivity : AppCompatActivity() {
    private val TEXT_STATE = "currentText"
    private lateinit var mTextView: TextView
    private lateinit var progressBar: ProgressBar

    private var mTask: SimpleAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        /** khôi phục textView*/
        if (savedInstanceState != null) {
            mTextView.text = savedInstanceState.getString(TEXT_STATE)
        }
    }

    private fun initView() {
        mTextView = findViewById(R.id.textView1)
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.INVISIBLE
    }

    fun startTask(view: View) {
        if (mTask != null && !mTask!!.isCancelled) {
            /**  Nếu công việc đang chạy, hủy nó*/
            mTask!!.cancel(true)
        } else {
            /** Nếu không, bắt đầu một công việc mới*/
            mTextView.setText(R.string.napping)
            progressBar.progress = 0

            mTask = SimpleAsyncTask(mTextView, progressBar)
            mTask!!.execute()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        /** Lưu trạng thái của TextView */
        outState.putString(TEXT_STATE, mTextView.text.toString())
    }
}
