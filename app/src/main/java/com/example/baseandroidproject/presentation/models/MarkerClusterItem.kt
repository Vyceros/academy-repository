package com.example.baseandroidproject.presentation.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MarkerClusterItem(
    private val position: LatLng,
    private val title: String,
    private val snippet: String,
    val data: Location
) : ClusterItem {
    override fun getPosition(): LatLng = position
    override fun getTitle(): String = title
    override fun getSnippet(): String = snippet
}
