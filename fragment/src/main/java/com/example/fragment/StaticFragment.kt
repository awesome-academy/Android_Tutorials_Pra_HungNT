package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment.databinding.StaticFragmentBinding


class StaticFragment : Fragment() {
    private val binding: StaticFragmentBinding by lazy {
        StaticFragmentBinding.inflate(layoutInflater)
    }


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

    companion object {
        private const val YES = 0
        private const val NO = 1
    }
}

