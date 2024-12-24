package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.data.ShopItem
import com.example.baseandroidproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var shopItemAdapter: ShopItemAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    private var itemsList = mutableListOf(
        ShopItem(id = 1, "Belt suit blazer", R.drawable.image3, "$200", "Party"),
        ShopItem(id = 2, "Belt suit blazer", R.drawable.image_2, "$200", "Camping"),
        ShopItem(id = 3, "Belt suit blazer", R.drawable.image3, "$200", "Category1"),
        ShopItem(id = 4, "Belt suit blazer", R.drawable.image_4, "$200", "Category1"),
        ShopItem(id = 5, "Belt suit blazer", R.drawable.image3, "$200", "Party"),
        ShopItem(id = 6, "Belt suit blazer", R.drawable.image_4, "$200", "All"),
    )
    private var filteredList = mutableListOf<ShopItem>()
    private var categoryList =
        mutableListOf(
            "All",
            "Party",
            "Camping",
            "Category1",
            "Category2",
            "Category3"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setupShopRecyclerView()
        setUpCategoryRecyclerView()
    }

    private fun setupShopRecyclerView() {
        val shopRecyclerView = binding.rvShopItems
        filteredList = itemsList
        shopItemAdapter = ShopItemAdapter(filteredList)

        shopRecyclerView.layoutManager =
            GridLayoutManager(this, 2)

        shopRecyclerView.adapter = shopItemAdapter
    }

    private fun setUpCategoryRecyclerView() {
        val categoryRecycler = binding.rvCategoryList

        categoryAdapter = CategoryAdapter(categoryList)

        categoryRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        categoryRecycler.adapter = categoryAdapter

        categoryAdapter.onClickListener {
            filterItem(it)
        }


    }

    private fun filterItem(category: String) {
        filteredList = if (category == "All") {
            itemsList.toMutableList()
        } else {
            itemsList.filter { it.category == category }.toMutableList()
        }
        shopItemAdapter.filterItem(filteredList)
    }
}


