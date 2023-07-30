package com.example.powerreceiver

import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private val mReceiver = CustomReceiver()

    companion object {
        private const val ACTION_CUSTOM_BROADCAST =
            "com.example.powerreceiver.ACTION_CUSTOM_BROADCAST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)

        /** Register the receiver*/
        registerReceiver(mReceiver, filter)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))
    }

    override fun onDestroy() {
        /** Unregister the receiver.*/
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)

        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(mReceiver)
    }
}
