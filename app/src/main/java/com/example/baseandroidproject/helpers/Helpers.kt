package com.example.baseandroidproject.helpers

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.PopupMenu
import java.util.Calendar

fun showDatePickerDialog(context: Context, onDateSelected: (String) -> Unit) {
    with(Calendar.getInstance()) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(selectedDate)
            },
            get(Calendar.YEAR),
            get(Calendar.MONTH),
            get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}

fun showGenderDropDown(anchorView: View, options: List<String>, onOptionSelected: (String) -> Unit
) {
    val popup = PopupMenu(anchorView.context, anchorView)
    options.forEachIndexed { index, option ->
        popup.menu.add(0, index, index, option)
    }
    popup.setOnMenuItemClickListener { menuItem ->
        onOptionSelected(menuItem.title.toString())
        true
    }
    popup.show()
}