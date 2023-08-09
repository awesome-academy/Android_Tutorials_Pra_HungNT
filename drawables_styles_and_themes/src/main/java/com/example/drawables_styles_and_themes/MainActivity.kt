package com.example.drawables_styles_and_themes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.drawables_styles_and_themes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.decreaseTeam1.setOnClickListener { score(it) }
        binding.decreaseTeam2.setOnClickListener { score(it) }
        binding.increaseTeam1.setOnClickListener { score(it) }
        binding.increaseTeam2.setOnClickListener { score(it) }

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)

            binding.scoreTeam1.text = mScore1.toString()
            binding.scoreTeam2.text = mScore2.toString()
        }
    }


    fun score(view: View) {
        when (view.id) {
            R.id.increaseTeam1 -> {
                mScore1++
                binding.scoreTeam1.text = mScore1.toString()
            }
            R.id.increaseTeam2 -> {
                mScore2++
                binding.scoreTeam2.text = mScore2.toString()
            }
            R.id.decreaseTeam1 -> {
                if(mScore1 > 0) {
                    mScore1--
                    binding.scoreTeam1.text = mScore1.toString()
                }
            }
            R.id.decreaseTeam2 -> {
                if(mScore2 > 0) {
                    mScore2--
                    binding.scoreTeam2.text = mScore2.toString()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val nightMode = AppCompatDelegate.getDefaultNightMode()
        val titleResId = if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            R.string.day_mode
        } else {
            R.string.night_mode
        }
        menu.findItem(R.id.night_mode).title = getString(titleResId)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val newNightMode = if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            AppCompatDelegate.setDefaultNightMode(newNightMode)
            recreate()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val STATE_SCORE_1 = "Team 1"
        private const val STATE_SCORE_2 = "Team 2"

        private var mScore1: Int = 0
        private var mScore2: Int = 0
    }
}

