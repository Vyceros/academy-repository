package com.example.baseandroidproject.presentation.fragments.maps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.FragmentMapsBinding
import com.example.baseandroidproject.presentation.fragments.base.BaseFragment
import com.example.baseandroidproject.presentation.fragments.bottomsheet.BottomFragment
import com.example.baseandroidproject.presentation.models.MarkerClusterItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : BaseFragment<FragmentMapsBinding>(FragmentMapsBinding::inflate) {

    private val viewModel: MapsViewModel by viewModels()
    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val callback = OnMapReadyCallback { map ->
        googleMap = map
        googleMap?.uiSettings?.isZoomControlsEnabled = true

        setupMarkerClickListener()
        setUpClusterManager()
    }

    override fun setup() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        requestPermissions()
    }

    private fun observeMarkers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    binding.progressBar.isVisible = state.isLoading

                    googleMap?.let { map ->
                        viewModel.addToCluster(map)


                    }
                }
            }
        }
    }

    private fun setUpClusterManager() {
        val clusterManager = ClusterManager<MarkerClusterItem>(requireContext(), googleMap)
        googleMap?.setOnCameraIdleListener(clusterManager)
        googleMap?.setOnMarkerClickListener(clusterManager)

        clusterManager.setOnClusterItemClickListener {
            setupMarkerClickListener()
            true
        }
        viewModel.clusterManager = clusterManager
    }

    private fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    getUserLocation()
                    observeMarkers()
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    getUserLocation()
                    observeMarkers()
                }

                else -> {
                    showPermissionRationale {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", requireContext().packageName, null)
                        }
                        startActivity(intent)
                    }
                }
            }
        }

        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            showPermissionRationale {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }

        } else {

            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun showPermissionRationale(onDialogShown: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle("LOCATION PERMISSION REQUIRED")
            .setMessage("you need to provide location permission to use this app its just a map")
            .setPositiveButton("OK") { _, _ ->
                onDialogShown()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun getUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val userLatLng = LatLng(location.latitude, location.longitude)
                googleMap?.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        userLatLng,
                        10f
                    )
                )
                googleMap?.addMarker(MarkerOptions().position(userLatLng).title("You are here"))
            }
        }
    }

    private fun setupMarkerClickListener() {
        googleMap?.setOnMarkerClickListener { marker ->
            val title = marker.title ?: "No Title"
            val position = marker.position

            val bottomSheet = BottomFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString("latitude", position.latitude.toString())
                    putString("longitude", position.longitude.toString())
                }
            }

            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            true
        }
    }


}


