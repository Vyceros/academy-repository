package com.example.senderbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


//Dynamic receiver
class AirplaneModeReceiver(private val callback : (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return
        if (isAirplaneModeEnabled) {
            callback(true)
            Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show()
        } else {
            callback(false)
            Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()

        }
    }
}