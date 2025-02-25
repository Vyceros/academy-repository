package com.example.baseandroidproject.presentation.utils.view_extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.R

fun ImageView.loadImagesGlide(url: String) {
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_launcher_background)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}