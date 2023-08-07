package com.example.fragment_lifecycle_and_communications

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import com.example.fragment_lifecycle_and_communications.databinding.ActivitySongDetailBinding

class SongDetailActivity : AppCompatActivity() {
    private val binding: ActivitySongDetailBinding by lazy {
        ActivitySongDetailBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /**
         * Set the toolbar
         * */
        setSupportActionBar(binding.detailToolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val selectedSong = intent.getIntExtra(SongUtils.SONG_ID_KEY, 0)
            val fragment = SongDetailFragment.newInstance(selectedSong)
            supportFragmentManager.beginTransaction()
                .add(binding.songDetailContainer.id, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        /**
         * check user press back home
         */
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

