package com.example.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    private lateinit var mBookInput: EditText
    private lateinit var mTitleText: TextView
    private lateinit var mAuthorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        if (supportLoaderManager.getLoader<String>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
    }
    /**ánh xạ view**/
    private fun initView() {
        mBookInput = findViewById(R.id.bookInput)
        mTitleText = findViewById(R.id.titleText)
        mAuthorText = findViewById(R.id.authorText)
    }

    fun searchBooks(view: View) {
        /** get key search book **/
        val queryString = mBookInput.text.toString()

        /** ẩn bàn phím khi nhấn nút **/
        val inputManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

        /** kiểm tra kết nối internet **/
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo: NetworkInfo? = connMgr?.activeNetworkInfo

        /** Nếu thỏa mãn các điều kiện về network cũng như query not empty
         thì start BookLoader AsyncTask*/
        if (networkInfo != null && networkInfo.isConnected && queryString.isNotEmpty()) {
            val queryBundle = Bundle()
            queryBundle.putString("queryString", queryString)
            supportLoaderManager.restartLoader(0, queryBundle, this)

            mAuthorText.text = ""
            mTitleText.setText(R.string.loading)
        }
        else {
            if (queryString.isEmpty()) {
                mAuthorText.text = ""
                mTitleText.setText(R.string.no_search_term)
            } else {
                mAuthorText.text = ""
                mTitleText.setText(R.string.no_network)
            }
        }
    }
/** Đây là phương thức ghi đè của giao diện LoaderCallbacks.
 *  Nó được gọi khi LoaderManager cần tạo một Loader mới **/
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        val queryString = args?.getString("queryString") ?: ""
        return BookLoader(this, queryString)
    }
/** Đây là phương thức ghi đè của giao diện LoaderCallbacks.
 *  Nó được gọi khi Loader đã hoàn thành việc tải dữ liệu. **/
    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {
            /**  Convert the response into a JSON object. */
            val jsonObject = JSONObject(data)
            /** Get the JSONArray of book items. */
            val itemsArray = jsonObject.getJSONArray("items")

            var i = 0
            var title: String? = null
            var authors: String? = null

            /** Tìm kiếm kết quả trong mảng itemsArray
             * Thoát khi tìm kiếm được kết quả hay tất cả các bản ghi đã được ktra
             */
            while (i < itemsArray.length() && (authors == null && title == null)) {
                /**
                * lấy thông tin của phần tử thứu i: hiện tại
                 */
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject("volumeInfo")

                try {
                    /**
                     * lấy tác giả và tiêu đề book
                     */
                    title = volumeInfo.getString("title")
                    val authorsArray = volumeInfo.getJSONArray("authors")
                    if (authorsArray.length() > 0) {
                        authors = authorsArray.getString(0)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                i++
            }

            if (title != null && authors != null) {
                mTitleText.text = title
                mAuthorText.text = authors
            } else {
                mTitleText.setText(R.string.no_results)
                mAuthorText.text = ""
            }

        } catch (e: Exception) {
            mTitleText.setText(R.string.no_results)
            mAuthorText.text = ""
            e.printStackTrace()
        }
    }
/** Đây là phương thức ghi đè của giao diện LoaderCallbacks.
 * Nó được gọi khi Loader đã bị reset (đặt lại) hoặc khi activity bị hủy   **/
    override fun onLoaderReset(loader: Loader<String>) {
        // Do nothing. Required by interface.
    }
}
