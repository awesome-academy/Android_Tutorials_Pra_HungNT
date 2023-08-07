package com.example.fragment_lifecycle_and_communications

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment_lifecycle_and_communications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /**
         * Set the toolbar
         * */
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = title

        binding.songList.adapter = SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS)
    }
}
