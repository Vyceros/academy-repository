package com.example.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.baseandroidproject.data.OrderStatus
import com.example.baseandroidproject.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            "details",
            this
        ) { _, bundle ->
            val orderId = bundle.getString("id", "Error") ?: "Error"
            val tracking = bundle.getString("tracking", "Error") ?: "Error"
            val status = bundle.getString("status", "Error") ?: "Error"
            val price = bundle.getString("price", "Error") ?: "Error"

            populateDetails(orderId, tracking, status, price)
        }
        listeners()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listeners() {

        binding.btnChangeOrderStatusDelivered.setOnClickListener {
            updateOrderStatus(OrderStatus.Delivered)
        }

        binding.btnChangeOrderStatusCancelled.setOnClickListener {
            updateOrderStatus(OrderStatus.Cancelled)
        }

        binding.btnGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun populateDetails(id: String, trackNumber: String, status: String, price: String) {
        binding.apply {
            tvOrderNumber.text = id
            tvOrderTracking.text = getString(R.string.track_number, trackNumber)
            tvOrderStatus.text = status
            tvOrderTotalPrice.text = getString(R.string.price, price)
        }
    }

    private fun updateOrderStatus(newStatus: OrderStatus) {
        val currentStatus = binding.tvOrderStatus.text.toString()

        if (currentStatus.lowercase() !in listOf("cancelled", "delivered")) {

            val id = binding.tvOrderNumber.text.toString()

            parentFragmentManager.setFragmentResult(
                "statusUpdate",

                bundleOf(
                    "id" to id,
                    "status" to newStatus.name


                )
            )

            parentFragmentManager.popBackStack()
        }

    }

}