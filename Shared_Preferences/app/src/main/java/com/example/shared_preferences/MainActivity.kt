package com.example.shared_preferences

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var mPreferences: SharedPreferences
    private val sharedPrefFile = "com.example.shared_preferences"
    /** Current count*/
    private var mCount = 0
    /** Current background color*/
    private var mColor: Int = 0
    /** Text view to display both count and color*/
    private lateinit var mShowCountTextView: TextView

    /** Key for current count*/
    private val COUNT_KEY = "count"
    /** Key for current color*/
    private val COLOR_KEY = "color"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** khởi tạo views, color, preferences*/
        mShowCountTextView = findViewById(R.id.count_textview)
        mColor = ContextCompat.getColor(this, R.color.default_background)

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        /** khôi phục preferences*/
        mCount = mPreferences.getInt(COUNT_KEY, 0)
        mShowCountTextView.text = mCount.toString()
        mColor = mPreferences.getInt(COLOR_KEY, mColor)
        mShowCountTextView.setBackgroundColor(mColor)
    }

    fun changeBackground(view: View) {
        val color = (view.background as ColorDrawable).color
        mShowCountTextView.setBackgroundColor(color)
        mColor = color
    }

    fun countUp(view: View) {
        mCount++
        mShowCountTextView.text = mCount.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // outState.putInt(COUNT_KEY, mCount)
        // outState.putInt(COLOR_KEY, mColor)
    }

    fun reset(view: View) {
        /** Reset count*/
        mCount = 0
        mShowCountTextView.text = mCount.toString()

        /** Reset color*/
        mColor = ContextCompat.getColor(this, R.color.default_background)
        mShowCountTextView.setBackgroundColor(mColor)

        /** Clear preferences*/
        val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()
    }

    override fun onPause() {
        super.onPause()
        /** lưu giữ trạng thái  Count và Color vào SharedPreferences sử dụng apply()*/
        val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
        preferencesEditor.putInt(COUNT_KEY, mCount)
        preferencesEditor.putInt(COLOR_KEY, mColor)
        preferencesEditor.apply()
    }
}
