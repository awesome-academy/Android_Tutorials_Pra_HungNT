package com.example.content_provider

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val arrayList = ArrayList<ContactModel>()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        /**  Check permission contact */
        checkPermission()
    }

    /**
     * kiểm tra quyền truy cập vào danh bạ READ_CONTACTS
     * Phải khai báo quyền trước trong file Manafests
     */

    private fun checkPermission() {
        /**
         * nếu quyền chưa được cấp
         * yêu cầu quyền bằng requestPermissions
         */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                100
            )
        } else {
            /**
             * nếu quyền đã được cấp thì getContactList()
             */
            getContactList()
        }
    }

    /**
     * get List contacts
     * display on recyclerView
     */
    @SuppressLint("Range")
    private fun getContactList() {
        val uri: Uri = ContactsContract.Contacts.CONTENT_URI

        /**
         * sắp xếp list contact theo tên
         */
        val sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"

        /**
         * câu lệnh truy vấn sử dụng contentResolver
         */
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, sort)

        /**
         * kiểm tra ds trả về có empty hay không
         * nếu count>0
         */
        if (cursor?.count ?: 0 > 0) {
            /**
             * lấy ra và khởi tạo các đối tượng ContactModel
             * và add vào arrayList
             */
            while (cursor?.moveToNext() == true) {
                val id: String = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID)
                )
                val name: String = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                )
                val uriPhone: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

                val selection =
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"
                val phoneCursor: Cursor? = contentResolver.query(
                    uriPhone,
                    null,
                    selection,
                    arrayOf(id),
                    null
                )

                // Check condition
                if (phoneCursor?.moveToNext() == true) {
                    val number: String = phoneCursor.getString(
                        phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    )

                    val model = ContactModel()
                    model.name = name
                    model.number = number

                    arrayList.add(model)
                    phoneCursor.close()
                }
            }
            cursor?.close()
        }

        /**
         * hiển thị arrayList lên RecyclerView
         */
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this, arrayList)
        recyclerView.adapter = adapter
    }

    /**
     * request Permission Result
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        /**
         * Người dùng cấp quyền
         */
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContactList()
        } else {
            /**
             * người dùng từ chối
             */
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            checkPermission()
        }
    }
}