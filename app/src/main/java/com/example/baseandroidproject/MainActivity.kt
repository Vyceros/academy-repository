package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.data.ShopItem
import com.example.baseandroidproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var itemsList = mutableListOf(
        ShopItem(id = 1, "Belt suit blazer", R.drawable.image3, "$200", "All"),
        ShopItem(id = 1, "Belt suit blazer", R.drawable.image_2, "$200", "All"),
        ShopItem(id = 1, "Belt suit blazer", R.drawable.image3, "$200", "All"),
        ShopItem(id = 1, "Belt suit blazer", R.drawable.image_4, "$200", "All"),
        ShopItem(id = 1, "Belt suit blazer", R.drawable.image3, "$200", "All"),
        ShopItem(id = 1, "Belt suit blazer", R.drawable.image_4, "$200", "All"),
    )
    private var categoryList =
        mutableListOf("All", "\uD83C\uDF89 Party", "\uD83C\uDFD5 Camping", "⛱ Category1", "⛱ Category2", "⛱ Category3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupShopRecyclerView()
        setUpCategoryRecyclerView()


    }


    private fun setupShopRecyclerView() {
        val shopRecyclerView = binding.rvShopItems
        shopRecyclerView.layoutManager =
            GridLayoutManager(this,2)
        shopRecyclerView.adapter = ShopItemAdapter(itemsList)
    }

    private fun setUpCategoryRecyclerView() {
        val categoryRecycler = binding.rvCategoryList
        categoryRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryRecycler.adapter = CategoryAdapter(categoryList)
    }
}