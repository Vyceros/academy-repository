package com.example.baseandroidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.baseandroidproject.databinding.FragmentMainDisplayBinding
import com.example.baseandroidproject.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainDisplayFragment : Fragment() {
    //this is default main fragment, so active orders are default
    private var _binding: FragmentMainDisplayBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager2 : ViewPager2
    private lateinit var tabLayouts : TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainDisplayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpTabLayout(){
        with(binding){
            tabLayouts = tabLayout
            viewPager2 = viewPager
        }

        viewPager2.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayouts,viewPager2){ tab, pos ->
            tab.text = when(pos){
                0 -> "Active"
                1 -> "Completed"
                else -> {
                    "Out of bound?"
                }
            }
        }.attach()


    }
}