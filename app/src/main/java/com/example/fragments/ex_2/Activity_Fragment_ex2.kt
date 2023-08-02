package com.example.fragments.ex_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.R
import com.example.databinding.ActivityFragmentEx2Binding
class Activity_Fragment_ex2 : AppCompatActivity() {
    private val binding:ActivityFragmentEx2Binding by lazy {
        ActivityFragmentEx2Binding.inflate(layoutInflater)
    }
    private var isFragmentDisplayed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                binding.openButton.setText(R.string.close)
            }
        }
        /**
         * Onclick
         */
        binding.openButton.setOnClickListener(View.OnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        })
    }

    /**
     * show Fragment using fragmentManager
     */

    fun displayFragment() {
        val fragment_ex2 = Fragment_ex2.newInstance()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()
        fragmentTransaction.add(
            R.id.fragment_container_2,
            fragment_ex2
        ).addToBackStack(null).commit()
        binding.openButton!!.setText(R.string.close)
        isFragmentDisplayed = true
    }


    /**
     * close Fragment
     */
    fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val fragment_ex2 = fragmentManager
            .findFragmentById(R.id.fragment_container_2) as Fragment_ex2?
        if (fragment_ex2 != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment_ex2).commit()
        }
        binding.openButton!!.setText(R.string.open)
        isFragmentDisplayed = false
    }

    /**
     * save Fragment state
     */
    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }

    companion object {
        const val STATE_FRAGMENT = "state_of_fragment"
    }
}

