package com.example.broadcasts

import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import com.example.broadcasts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mReceiver = PowerReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)

        this.registerReceiver(mReceiver, filter)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))

        binding.buttonSendBroadcast.setOnClickListener{sendPowerBroadcast(it)}
    }


    fun sendPowerBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy()
    }

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "com.example.broadcasts.ACTION_POWER_BROADCAST"
    }
}


