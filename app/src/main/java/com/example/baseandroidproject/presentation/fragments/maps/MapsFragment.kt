package com.example.baseandroidproject.presentation.fragments.maps

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.FragmentMapsBinding
import com.example.baseandroidproject.presentation.fragments.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : BaseFragment<FragmentMapsBinding>(FragmentMapsBinding::inflate) {

    private val viewModel: MapsViewModel by viewModels()
    private var googleMap: GoogleMap? = null

    private val callback = OnMapReadyCallback { map ->
        googleMap = map
        googleMap?.uiSettings?.isZoomControlsEnabled = true

        observeMarkers()
    }

    override fun setup() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun observeMarkers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    binding.progressBar.isVisible = state.isLoading

                    googleMap?.let { map ->
                        map.clear()

                        state.markers.forEach { location ->
                            val position = LatLng(location.lat, location.lan)
                            map.addMarker(
                                MarkerOptions()
                                    .position(position)
                                    .title(location.title)
                                    .snippet(location.address)
                            )
                        }
                    }
                }
            }
        }
    }
}


