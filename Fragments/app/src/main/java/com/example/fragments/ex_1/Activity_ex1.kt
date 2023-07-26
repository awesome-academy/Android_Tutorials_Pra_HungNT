package com.example.fragments.ex_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragments.R
import com.example.fragments.databinding.ActivityEx1Binding


class Activity_ex1 : AppCompatActivity() {

    private val binding: ActivityEx1Binding by lazy {
        ActivityEx1Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}




