package com.example.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.broadcasts.MainActivity.Companion.ACTION_CUSTOM_BROADCAST

class PowerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action

        intentAction?.let {
            val toastMessage = when (it) {
                Intent.ACTION_POWER_CONNECTED -> context.getString(R.string.power_connected)
                Intent.ACTION_POWER_DISCONNECTED -> context.getString(R.string.power_disconnected)
                ACTION_CUSTOM_BROADCAST -> context.getString(R.string.custom_broadcast_received)
                else -> context.getString(R.string.unknown_intent_action)
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}


