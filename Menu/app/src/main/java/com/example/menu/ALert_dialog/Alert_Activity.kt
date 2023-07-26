package com.example.menu.ALert_dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.menu.R

class Alert_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)
    }

    fun onClickShowAlert(view: View) {
        val myAlertBuilder = AlertDialog.Builder(this)
        myAlertBuilder.setTitle("Alert")
        myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:")
        myAlertBuilder.setPositiveButton("OK") { dialog, which ->
            Toast.makeText(applicationContext, "Pressed OK", Toast.LENGTH_SHORT).show()
        }
        myAlertBuilder.setNegativeButton("Cancel") { dialog, which ->
            Toast.makeText(applicationContext, "Pressed Cancel", Toast.LENGTH_SHORT).show()
        }
        myAlertBuilder.show()
    }
}