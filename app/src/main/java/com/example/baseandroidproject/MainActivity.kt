package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.data.Address
import com.example.baseandroidproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val addressAdapter by lazy {
        AddressAdapter()
    }

    private val listOfAddresses = mutableListOf(
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
        Address(1, R.drawable.back_button, "racxa", "racxarucxaracxarucxa"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        addNewAddress()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val recycler = binding.recyclerView

        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = addressAdapter
        }
        addressAdapter.submitList(listOfAddresses)
    }

    private fun addNewAddress() {
        binding.btnAddNew.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, NewAddressFragment(),"NewAddress")
                addToBackStack("NewAddress")
                commit()
            }
        }
    }
}