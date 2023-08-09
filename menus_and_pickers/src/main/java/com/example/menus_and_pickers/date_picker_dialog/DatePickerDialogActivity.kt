package com.example.menus_and_pickers.date_picker_dialog

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import com.example.menus_and_pickers.databinding.ActivityDatePickerDialogBinding
import java.util.*

class DatePickerDialogActivity : AppCompatActivity() {
    private val binding: ActivityDatePickerDialogBinding by lazy{
        ActivityDatePickerDialogBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonDatepicker.setOnClickListener { showDatePickerDialog() }
    }

    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this,
            { selectedDatePicker, selectedYear, selectedMonth, selectedDay ->
                val selectedTime = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, selectedDay)
                }

                val formattedTime = DateFormat.format(FORMAT_DATE, selectedTime).toString()
                showToast("Selected date: $formattedTime")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    companion object{
        private const val FORMAT_DATE = "dd/MM/yyyy"
    }
}
