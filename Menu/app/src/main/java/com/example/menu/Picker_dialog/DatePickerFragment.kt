package com.example.menu.Picker_dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.NonNull
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    /** Thay thế cho onCreateView()*/
    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        /** Sử dụng ngày hiện tại làm ngày mặc định trong DatePickerDialog.*/
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        /** Tạo một instance mới của DatePickerDialog và trả về.*/
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val activity = requireActivity() as Date_Picker_Dialog_Activity
        activity.processDatePickerResult(year, month, dayOfMonth)
    }
}