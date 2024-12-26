package com.example.baseandroidproject

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.data.Address
import com.example.baseandroidproject.data.AddressStorage
import com.example.baseandroidproject.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val addressAdapter by lazy {
        AddressAdapter()
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

        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        parentFragmentManager.setFragmentResultListener("submitted", this) { key, bundle ->
            val isSubmit = bundle.getBoolean("submitted")
            if (isSubmit) {
                addressAdapter.submitList(AddressStorage.list)
                d("submitListHitpoint", "submit list")
            }
        }
    }

    private fun init() {
        moveToNewAddressScreen()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val recycler = binding.recyclerView

        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = addressAdapter
        }

        addressAdapter.onDeleteClick { address ->
            deleteAddress(address)
        }

        addressAdapter.onEditClick { address ->
            goToEditScreen(address)

        }
    }

    private fun moveToNewAddressScreen() {
        binding.btnAddNew.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.main, NewAddressFragment(), "NewAddress")
                addToBackStack("NewAddress")
                commit()
            }
        }
    }

    private fun deleteAddress(address: Address) {
        val index = AddressStorage.list.indexOf(address)
        if (index != -1) {
            AddressStorage.list.removeAt(index)
            addressAdapter.notifyItemRemoved(index)
        }
    }

    private fun goToEditScreen(address: Address) {
        if (address.isSelected) {
            val bundle = Bundle().apply {
                putString("title", address.title)
                putString("address", address.address)
                putInt("id",address.id)
            }
            val editFragment = EditFragment().apply {
                arguments = bundle
            }
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.main, editFragment, "EditAddress")
                addToBackStack("EditAddress")
                commit()
            }
        } else {
            Snackbar.make(
                binding.root,
                getString(R.string.address_not_active_warning), Snackbar.LENGTH_SHORT
            ).show()
        }


    }


}