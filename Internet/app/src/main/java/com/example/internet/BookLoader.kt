package com.example.internet

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
/**thực hiện việc tải thông tin sách từ internet**/
class BookLoader(context: Context, private val mQueryString: String) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }
/**thực hiện tải dữ liệu trong nền và trả về kết quả dưới dạng một chuỗi JSON.**/
    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(mQueryString)
    }
}
