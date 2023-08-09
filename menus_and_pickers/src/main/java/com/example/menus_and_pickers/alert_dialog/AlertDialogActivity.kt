package com.example.menus_and_pickers.alert_dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.menus_and_pickers.R
import com.example.menus_and_pickers.databinding.ActivityAlertDialogBinding


class AlertDialogActivity : AppCompatActivity() {

    private val binding: ActivityAlertDialogBinding by lazy{
        ActivityAlertDialogBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAlert.setOnClickListener { ShowAlert(it) }
    }

    fun ShowAlert(view: View) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.alert_title))
            .setMessage(getString(R.string.alert_message))
            .setPositiveButton(getString(R.string.button_ok)) { dialog, which ->
                showToast(getString(R.string.pressed_ok))
            }
            .setNegativeButton(getString(R.string.button_cancel)) { dialog, which ->
                showToast(getString(R.string.pressed_cancel))
            }
            .show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
