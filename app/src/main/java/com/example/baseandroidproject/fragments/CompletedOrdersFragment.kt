package com.example.baseandroidproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.adapters.BottomDialog
import com.example.baseandroidproject.data.OrderStatus
import com.example.baseandroidproject.data.Storage
import com.example.baseandroidproject.databinding.FragmentCompletedOrdersBinding
import com.example.baseandroidproject.adapters.OrdersAdapter

class CompletedOrdersFragment : Fragment() {
    // this is to show completed orders
    private var _binding : FragmentCompletedOrdersBinding? = null
    private val binding get() = _binding!!

    private lateinit var ordersAdapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompletedOrdersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        filterByOrdreStatus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun filterByOrdreStatus(){
        val filteredByActive = Storage.listOfOrders.filter { it.status == OrderStatus.Completed }
        ordersAdapter.submitList(filteredByActive)

    }

    private fun setUpRecycler() {
        ordersAdapter = OrdersAdapter(
            onReviewClick = { id ->
                openUpBottomDialog(id)
            }
        )
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ordersAdapter
        }
    }

    private fun openUpBottomDialog(id : String){
        val bottomDialogSheet = BottomDialog().apply {
            arguments = Bundle().apply {
                putString("orderId",id)
            }
        }
        bottomDialogSheet.show(childFragmentManager,bottomDialogSheet.tag)
    }
}