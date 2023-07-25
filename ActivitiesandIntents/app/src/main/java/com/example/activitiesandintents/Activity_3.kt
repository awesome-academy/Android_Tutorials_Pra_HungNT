package com.example.activitiesandintents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activitiesandintents.databinding.Activity3Binding


class Activity_3 : AppCompatActivity() {

    private val binding: Activity3Binding by lazy {
        Activity3Binding.inflate(layoutInflater)
    }

    companion object {
        const val SEND_MESSAGE_KEY = "send_message_activity_4"
        const val REPLY_MESSAGE_KEY = "reply_message"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /**
         * receiver
         */
        var receiver = intent.getStringExtra(REPLY_MESSAGE_KEY)
        binding.textMessageMain.setText(receiver)


        /**
         * send_message
         */
        binding.buttonSendToActivity4.setOnClickListener {
            val intent = Intent(this, Activity_4::class.java)
            var send = binding.textSendToActivity4.text.toString().trim()
            intent.putExtra(SEND_MESSAGE_KEY, send)
            startActivity(intent)
        }
    }
}



