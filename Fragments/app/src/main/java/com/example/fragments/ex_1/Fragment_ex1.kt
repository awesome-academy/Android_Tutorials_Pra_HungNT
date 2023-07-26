package com.example.fragments.ex_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentEx1Binding



class Fragment_ex1 : Fragment() {
    private val binding:FragmentEx1Binding by lazy {
        FragmentEx1Binding.inflate(layoutInflater)
    }

    private val YES = 0
    private val NO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = binding.root

        val radioGroup = binding.radioGroup1

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = binding.root.findViewById<View>(checkedId)
            val index = binding.radioGroup1.indexOfChild(radioButton)
            val textView = binding.fragmentHeader1
            when (index) {
                YES -> textView.setText(R.string.yes_message)
                NO -> textView.setText(R.string.no_message)
                else -> {
                }
            }
        }

        return rootView
    }
}





