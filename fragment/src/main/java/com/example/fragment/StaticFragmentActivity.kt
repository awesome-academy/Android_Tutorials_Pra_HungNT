package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragment.databinding.ActivityStaticFragmentBinding

class StaticFragmentActivity : AppCompatActivity() {
    private val  binding: ActivityStaticFragmentBinding by lazy {
        ActivityStaticFragmentBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}

