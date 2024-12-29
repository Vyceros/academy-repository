package com.example.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending),
            Order(1231322312, 12292024L, "2312932EqQWR", 1, 2342, OrderStatus.Pending)
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
}