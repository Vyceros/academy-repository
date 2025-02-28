package com.example.baseandroidproject.presentation.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionHandler(private val fragment: Fragment) {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var onPermissionGranted: (() -> Unit)? = null

    init {
        initPermissionLauncher()
    }

    private fun initPermissionLauncher() {
        permissionLauncher = fragment.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                checkLocationEnabled()
            } else {
                showRationaleDialog()
            }
        }
    }

    fun checkAndRequestLocationPermission(onGranted: () -> Unit) {
        this.onPermissionGranted = onGranted

        when {
            ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                checkLocationEnabled()
            }
            fragment.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showRationaleDialog()
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun checkLocationEnabled() {
        val locationManager = fragment.requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled && !isNetworkEnabled) {
            showLocationServiceDisabledDialog()
        } else {
            onPermissionGranted?.invoke()
        }
    }

    private fun showRationaleDialog() {
        AlertDialog.Builder(fragment.requireContext())
            .setTitle("Permission Denied")
            .setMessage("we need the permission to make the app work")
            .setPositiveButton("Ok") { _, _ ->
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            .setCancelable(false)
            .show()
    }



    private fun showLocationServiceDisabledDialog() {
        AlertDialog.Builder(fragment.requireContext())
            .setTitle("Permission Denied")
            .setMessage("we need the permission to make the app work")
            .setPositiveButton("Ok") { _, _ ->
                openLocationSettings()
            }
            .setCancelable(false)
            .show()
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = android.net.Uri.fromParts("package", fragment.requireContext().packageName, null)
        }
        fragment.startActivity(intent)
    }

    private fun openLocationSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        fragment.startActivity(intent)
    }
}