package com.example.baseandroidproject.presentation.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(context: Context, message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}