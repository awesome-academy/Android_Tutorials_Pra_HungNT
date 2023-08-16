package com.example.shared_preferences

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.shared_preferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private  val mPreferences: SharedPreferences by lazy {
        getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
    }
    private var mCount = 0
    private var mColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mColor = ContextCompat.getColor(this, R.color.default_background)
        mCount = mPreferences.getInt(COUNT_KEY, 0)
        binding.textCount.text = mCount.toString()

        mColor = mPreferences.getInt(COLOR_KEY, mColor)
        binding.textCount.setBackgroundColor(mColor)

        binding.buttonBlackBackground.setOnClickListener{changeBackground(it)}
        binding.buttonBlueBackground.setOnClickListener{changeBackground(it)}
        binding.buttonGreenBackground.setOnClickListener{changeBackground(it)}
        binding.buttonRedBackground.setOnClickListener{changeBackground(it)}

        binding.buttonCount.setOnClickListener{countUp(it)}
        binding.buttonReset.setOnClickListener{reset(it)}
    }

    fun changeBackground(view: View) {
        val color = (view.background as? ColorDrawable)?.color
        color?.let {
            binding.textCount.setBackgroundColor(it)
            mColor = it
        }
    }

    fun countUp(view: View) {
        mCount++
        binding.textCount.text = mCount.toString()
    }

    fun reset(view: View) {
        mCount = 0
        binding.textCount.text = mCount.toString()

        mColor = ContextCompat.getColor(this, R.color.default_background)
        binding.textCount.setBackgroundColor(mColor)

        val preferencesEditor = mPreferences.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferences.edit()
        preferencesEditor.putInt(COUNT_KEY, mCount)
        preferencesEditor.putInt(COLOR_KEY, mColor)
        preferencesEditor.apply()
    }

    companion object{
        private val sharedPrefFile = "com.example.shared_preferences"
        private val COUNT_KEY = "count"
        private val COLOR_KEY = "color"
    }
}


