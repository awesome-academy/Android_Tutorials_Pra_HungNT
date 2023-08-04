package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment.databinding.DynamicFragmentBinding


class DynamicFragment : Fragment() {
    private val binding: DynamicFragmentBinding by lazy {
        DynamicFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = binding.root
        val radioGroup = binding.radioGroup2
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
        fun newInstance(): DynamicFragment {
            return DynamicFragment()
        }
    }
}


