package com.example.user_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_navigation.databinding.FragmentTab1Binding

class TabFragment1 : Fragment() {
    private val binding:FragmentTab1Binding by lazy{
        FragmentTab1Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root
        return view
    }
}

