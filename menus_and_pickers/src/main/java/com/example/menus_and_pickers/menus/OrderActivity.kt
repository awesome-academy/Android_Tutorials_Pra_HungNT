package com.example.menus_and_pickers.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.menus_and_pickers.R
import com.example.menus_and_pickers.databinding.ActivityOrderBinding


class OrderActivity : AppCompatActivity() {
    private val binding: ActivityOrderBinding by lazy {
        ActivityOrderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            onRadioButtonClicked(group.findViewById(checkedId))
        }
    }

    fun onRadioButtonClicked(radioButton: RadioButton) {
        when (radioButton.id) {
            binding.radioSameday.id -> displayToast(getString(R.string.same_day_messenger_service))
            binding.radioNextday.id -> displayToast(getString(R.string.next_day_ground_delivery))
            binding.radioPickup.id -> displayToast(getString(R.string.pick_up))
        }
    }

    fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
