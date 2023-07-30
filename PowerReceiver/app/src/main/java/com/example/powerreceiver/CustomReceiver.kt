package com.example.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    companion object {
        private const val ACTION_CUSTOM_BROADCAST =
            "com.example.powerreceiver.ACTION_CUSTOM_BROADCAST"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action

        intentAction?.let {
            val toastMessage = when (it) {
                Intent.ACTION_POWER_CONNECTED -> "Power connected!"
                Intent.ACTION_POWER_DISCONNECTED -> "Power disconnected!"
                ACTION_CUSTOM_BROADCAST -> "Custom Broadcast Received"
                else -> "unknown intent action"
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
