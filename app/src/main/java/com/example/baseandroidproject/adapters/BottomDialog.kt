package com.example.baseandroidproject.adapters

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.Storage
import com.example.baseandroidproject.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class BottomDialog : BottomSheetDialogFragment() {
    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

    private var orderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            orderId = it.getString("orderId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateData()
        buttonListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populateData() {
        val order = Storage.listOfOrders.find { it.id.toString() == orderId }
        if (order != null) {
            with(binding) {
                tvProductName.text = order.name
                tvProductColor.text = order.color
                tvProductQuantity.text = getString(R.string.qty, order.quantity.toString())
                tvProductPrice.text = getString(R.string.order_price, order.price.toString())
                tvProductStatus.text = order.status.toString()
                tvColorCircle.background.setTint(Color.parseColor(order.color))
            }
        }

    }

    private fun buttonListeners() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnSubmit.setOnClickListener {
            val order = Storage.listOfOrders.find { it.id.toString() == orderId }
            with(binding) {
                if (ratingBar.rating >= 1 && etReview.text.toString().isNotEmpty()) {
                    order?.orderReview = etReview.text.toString()
                    dismiss()
                } else {
                    Snackbar.make(
                        binding.divider1,
                        "Give us some stars and proper review",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

}