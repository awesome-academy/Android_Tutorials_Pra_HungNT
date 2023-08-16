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
import com.example.content_provider.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val arrayList = ArrayList<ContactModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkPermission()
    }

    /**
     * checkPermission
     */
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                100
            )
        } else {
            getContactList()
        }
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
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContactList()
        } else {
            Toast.makeText(this, getString(R.string.Permission_Denied), Toast.LENGTH_SHORT).show()
            checkPermission()
        }
    }

    /**
     * get List contacts
     * display on recyclerView
     */
    @SuppressLint("Range")
    private fun getContactList() {
        val uri: Uri = ContactsContract.Contacts.CONTENT_URI
        val sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, sort)

        if (cursor?.count ?: 0 > 0) {
            while (cursor?.moveToNext() == true) {
                val id: String = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID)
                )
                val name: String = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                )
                val uriPhone: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                val selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"
                val phoneCursor: Cursor? = contentResolver.query(
                    uriPhone,
                    null,
                    selection,
                    arrayOf(id),
                    null
                )

                if (phoneCursor?.moveToNext() == true) {
                    val number: String = phoneCursor.getString(
                        phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    )
                    if(name!=null && number!=null){
                        val contact = ContactModel(name,number)
                        arrayList.add(contact)
                    }
                    phoneCursor.close()
                }
            }
            cursor?.close()
        }

        binding.recyclerView.adapter = ContactAdapter(arrayList)
    }
}
