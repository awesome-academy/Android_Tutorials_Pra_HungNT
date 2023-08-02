package com.example.fragments.ex_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databinding.ActivityFragmentEx1Binding

class Activity_Fragment_ex1 : AppCompatActivity() {
    private val binding: ActivityFragmentEx1Binding by lazy {
        ActivityFragmentEx1Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}