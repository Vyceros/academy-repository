package com.example.baseandroidproject.helper

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun displaySnackbarMessage(view: View, text: String, status: Boolean) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(if (status) Color.GREEN else Color.RED)
        .setTextColor(Color.WHITE)
        .show()
}

fun changeDisplays(disappearView: View, appearView: View) {
    disappearView.visibility = View.GONE
    appearView.visibility = View.VISIBLE

}