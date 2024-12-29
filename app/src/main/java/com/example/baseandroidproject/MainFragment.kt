package com.example.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.data.Order
import com.example.baseandroidproject.data.OrderStatus
import com.example.baseandroidproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val orderAdapter by lazy {
        OrderAdapter()
    }

    private val orderList =
        mutableListOf(
            Order(
                date = System.currentTimeMillis(),
                trackNumber = "IK28372EQE21",
                quantity = 1,
                totalPrice = 2342,
                status = OrderStatus.Pending
            ),
            Order(
                date = System.currentTimeMillis(),
                trackNumber = "IK28372EQE21",
                quantity = 1,
                totalPrice = 2342,
                status = OrderStatus.Pending
            ),
            Order(
                date = System.currentTimeMillis(),
                trackNumber = "IK28372EQE21",
                quantity = 1,
                totalPrice = 2342,
                status = OrderStatus.Pending
            ),
            Order(
                date = System.currentTimeMillis(),
                trackNumber = "IK28372EQE21",
                quantity = 1,
                totalPrice = 2342,
                status = OrderStatus.Pending
            ),
            Order(
                date = System.currentTimeMillis(),
                trackNumber = "IK28372EQE21",
                quantity = 1,
                totalPrice = 2342,
                status = OrderStatus.Pending
            ),
            Order(
                date = System.currentTimeMillis(),
                trackNumber = "IK28372EQE21",
                quantity = 1,
                totalPrice = 2342,
                status = OrderStatus.Pending
            ),
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSetup()
        filterListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun recyclerSetup() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
        orderAdapter.submitList(orderList)

        orderAdapter.onDetail { details ->
            onDetailClick(details)
        }
    }

    override fun onResume() {
        super.onResume()
        listenForFragmentResult()
    }

    private fun filterListeners() {
        binding.tvDeliveredOrders.setOnClickListener {
            filterOrders(OrderStatus.Delivered)
        }
        binding.tvPendingOrders.setOnClickListener {
            filterOrders(OrderStatus.Pending)
        }
        binding.tvCancelledOrders.setOnClickListener {
            filterOrders(OrderStatus.Cancelled)
        }
    }

    private fun filterOrders(status: OrderStatus) {
        val filteredList = orderList.filter { it.status == status }
        orderAdapter.submitList(filteredList)
    }

    private fun onDetailClick(order: Order) {
        parentFragmentManager.setFragmentResult(
            "details",
            bundleOf(
                "id" to order.orderId.toString(),
                "tracking" to order.trackNumber,
                "status" to order.status.toString(),
                "price" to order.totalPrice.toString()
            )
        )

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, DetailsFragment(), "Details")
            addToBackStack("Details")
            commit()
        }
    }

    private fun listenForFragmentResult() {
        parentFragmentManager.setFragmentResultListener("statusUpdate", this) { _, bundle ->
            val id = bundle.getString("id") ?: ""
            val updatedStatus = bundle.getString("status") ?: ""

            orderList.find { it.orderId.toString() == id }?.let { order ->

                order.status = OrderStatus.valueOf(updatedStatus)

                orderAdapter.submitList(orderList.toList())
                filterOrders(OrderStatus.Pending)
            }
        }
    }
}