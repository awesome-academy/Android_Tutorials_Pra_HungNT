package com.example.activitiesandintents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.databinding.Activity4Binding



class Activity_4 : AppCompatActivity() {
    private val binding: Activity4Binding by lazy {
        Activity4Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /**
         * receiver message
         */
        val receiver = intent.getStringExtra(SEND_MESSAGE_KEY).toString().trim()
        binding.textMessage.setText(receiver)


        /**
         * reply
         */
        binding.buttonReplyMessage.setOnClickListener {
            val intent = Intent(this, Activity_3::class.java)
            var reply = binding.textReplyReceiver.text.toString().trim()
            intent.putExtra(REPLY_MESSAGE_KEY, reply)
            startActivity(intent)
        }
    }
    companion object {
        const val SEND_MESSAGE_KEY = "send_message_activity_4"
        const val REPLY_MESSAGE_KEY = "reply_message"
    }
}



