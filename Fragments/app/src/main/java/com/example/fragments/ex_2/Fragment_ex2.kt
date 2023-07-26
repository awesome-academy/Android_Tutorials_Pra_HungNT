package com.example.fragments.ex_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import com.example.fragments.R
import com.example.fragments.databinding.FragmentEx2Binding


class Fragment_ex2 : Fragment() {
    private val binding: FragmentEx2Binding by lazy {
        FragmentEx2Binding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = binding.root


        val radioGroup = binding.radioGroup2

        /** Set the radioGroup onCheckedChanged listener.*/
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = radioGroup.findViewById<View>(checkedId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView = binding.fragmentHeader2
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

        @JvmStatic
        fun newInstance(): Fragment_ex2 {
            return Fragment_ex2()
        }
    }


}

