package com.example.baseandroidproject.adapters

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.baseandroidproject.fragments.ActiveOrdersFragment
import com.example.baseandroidproject.fragments.CompletedOrdersFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ActiveOrdersFragment()
            1 -> CompletedOrdersFragment()
            else -> {throw NotFoundException()}
        }
    }
}