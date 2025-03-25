package com.example.baseandroidproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CustomReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.warmane.wowtracker.presentation.activity.TEST_BROADCAST"){
            Log.d("CustomBroadcast","Custom STATIC broadcast action received.")
        }
    }
}